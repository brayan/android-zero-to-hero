package br.com.sailboat.zerotohero

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}