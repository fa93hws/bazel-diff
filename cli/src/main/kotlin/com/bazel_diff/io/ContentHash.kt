package com.bazel_diff.io

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.nio.file.Files
import java.nio.file.Path


class ContentHash(path: Path?) {
    // filename relative to workspace -> content hash of the file
    var filenameToHash: Map<String, String>? = null

    init {
        path?.let {
            this.filenameToHash = readJson(it)
        }
    }

    private fun readJson(file: Path): Map<String, String> {
        val gson = Gson()
        val reader = Files.newBufferedReader(file)
        val shape = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson(reader, shape)
    }
}
