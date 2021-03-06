package com.grudus.snake.utils

fun <K, V> MutableMap<K, V>.invokeAndPutIfAbsent(key: K, getFunction: () -> V): V =
        if (containsKey(key)) this[key]!!
        else {
                val value = getFunction()
                put(key, value)
                value
        }