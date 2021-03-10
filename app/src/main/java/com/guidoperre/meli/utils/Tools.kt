package com.guidoperre.meli.utils

class Tools {

    companion object {

        fun parseGoogleResponse(response: String?): List<String> {
            val suggestions = ArrayList<String>()
            if (response != null){
                val split = response.split("[","]")
                if (split.size > 1){
                    val parsed = split[1].split(",")
                    for (suggest in parsed)
                        suggestions.add(suggest)
                }
            }
            return suggestions
        }

    }

}