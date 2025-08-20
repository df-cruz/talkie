package com.dfcruz.talkie.platform

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides information about the current network connectivity state.
 *
 */
@Singleton
class ConnectivityInfoProvider @Inject constructor(
    private val connectivityManager: ConnectivityManager,
) {

    private val _networkState = MutableStateFlow(
        if (isConnected()) NetworkState.CONNECTED else NetworkState.DISCONNECTED
    )
    val networkState: StateFlow<NetworkState> = _networkState.asStateFlow()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _networkState.value = NetworkState.CONNECTED
        }

        override fun onLost(network: Network) {
            _networkState.value = NetworkState.DISCONNECTED
        }
    }

    init {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    /**
     * Returns whether the device is currently connected to a validated network.
     *
     * @return true if connected to the internet, false otherwise.
     */
    fun isConnected(): Boolean = runCatching {
        with(connectivityManager) {
            this.getNetworkCapabilities(this.activeNetwork)?.run {
                hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            } ?: false
        }
    }.getOrElse { false }
}

/**
 * Represents high-level connectivity states.
 */
enum class NetworkState {
    CONNECTED,
    DISCONNECTED,
}