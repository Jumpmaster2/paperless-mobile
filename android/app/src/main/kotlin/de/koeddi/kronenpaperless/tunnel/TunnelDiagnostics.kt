package de.koeddi.kronenpaperless.tunnel

import com.wireguard.config.Config
import com.wireguard.crypto.Key
import java.net.Inet4Address
import java.net.Inet6Address

data class PeerCounters(val rx: Long, val tx: Long, val handshake: Long)

/** Only explicitly selected, non-secret fields cross the channel. */
fun diagnosticMap(config: Config, state: String, counters: Map<Key, PeerCounters> = emptyMap()): Map<String, Any?> = mapOf(
    "state" to state,
    "interfaceAddresses" to config.`interface`.addresses.map { it.toString() },
    "dnsServers" to config.`interface`.dnsServers.map { it.hostAddress },
    "searchDomains" to config.`interface`.dnsSearchDomains.toList(),
    "mtu" to config.`interface`.mtu.orElse(0).takeIf { it != 0 },
    "peers" to config.peers.map { peer ->
        val endpoint = peer.endpoint.orElse(null)
        val stats = counters[peer.publicKey]
        mapOf(
            "endpointHost" to endpoint?.host,
            "endpointPort" to endpoint?.port,
            "allowedIps" to peer.allowedIps.map { network ->
                if (network.mask == 0 && network.address.isAnyLocalAddress) {
                    when (network.address) {
                        is Inet4Address -> "0.0.0.0/0"
                        is Inet6Address -> "::/0"
                        else -> network.toString()
                    }
                } else network.toString()
            },
            "persistentKeepalive" to peer.persistentKeepalive.orElse(0).takeIf { it != 0 },
            "rxBytes" to (stats?.rx ?: 0L),
            "txBytes" to (stats?.tx ?: 0L),
            "lastHandshakeEpochMillis" to (stats?.handshake ?: 0L)
        )
    }
)
