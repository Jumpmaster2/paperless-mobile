package de.koeddi.kronenpaperless.tunnel

import org.junit.Assert.*
import org.junit.Test
import java.net.Inet4Address
import java.net.Inet6Address

class WgConfigTest {
    private val key = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA="
    private val config get() = """
        [Interface]
        PrivateKey = $key
        Address = 192.0.2.2/32
        DNS = 192.0.2.53
        IncludedApplications = org.example.other
        ExcludedApplications = org.example.excluded
        [Peer]
        PublicKey = $key
        AllowedIPs = 192.168.0.0/16
        Endpoint = vpn.example.com:51820
    """.trimIndent()

    @Test fun routesAllTrafficAndRemovesApplicationFilters() {
        val parsed = WgConfig.parseAndPin(config, "de.example.app.debug")
        assertTrue(parsed.`interface`.includedApplications.isEmpty())
        val routes = parsed.peers.first().allowedIps
        assertEquals(2, routes.size)
        assertTrue(routes.all { it.mask == 0 && it.address.isAnyLocalAddress })
        assertEquals(1, routes.count { it.address is Inet4Address })
        assertEquals(1, routes.count { it.address is Inet6Address })
        assertEquals("192.0.2.53", parsed.`interface`.dnsServers.first().hostAddress)
        assertTrue(parsed.`interface`.excludedApplications.isEmpty())
        assertEquals(1, parsed.peers.size)
    }

    @Test fun rejectsOversizedUtf8() {
        val error = org.junit.Assert.assertThrows(WgConfigException::class.java) {
            WgConfig.parseAndPin("ä".repeat(32769), "de.example.app")
        }
        assertEquals("config_too_large", error.code)
    }

    @Test fun rejectsInvalidWithoutExposingSecrets() {
        val secret = "invented-secret-marker"
        val error = org.junit.Assert.assertThrows(WgConfigException::class.java) {
            WgConfig.parseAndPin(config.replace(key, secret), "de.example.app")
        }
        assertEquals("config_invalid", error.code)
        assertFalse(error.toString().contains(secret))
        assertNull(error.cause)
    }

    @Test fun rejectsMissingPeerOrEndpoint() {
        for (invalid in listOf(config.substringBefore("[Peer]"), config.replace("Endpoint = vpn.example.com:51820", ""))) {
            assertEquals("config_invalid", org.junit.Assert.assertThrows(WgConfigException::class.java) {
                WgConfig.parseAndPin(invalid, "de.example.app")
            }.code)
        }
    }
}
