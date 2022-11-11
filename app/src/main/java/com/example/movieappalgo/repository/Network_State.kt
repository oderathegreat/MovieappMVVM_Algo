package com.example.movieappalgo.repository


enum class Status {
    RUNNING,
    SUCCESS,
    FAILED

}


class Network_State(val status: Status, val msg: String) {

    companion object {

        val ENDOFLIST:Network_State
        val ERROR: Network_State
        val LOADED: Network_State
        val LOADING: Network_State

        init {

            ERROR = Network_State(Status.FAILED, "Opps Something happened hold on")

            ENDOFLIST = Network_State(Status.FAILED, "Limit reached! Sorry")

            LOADED = Network_State(Status.SUCCESS, "Hurray! Success")

            LOADING = Network_State(Status.RUNNING, "Relax! We are currently getting some data")


        }


    }


}