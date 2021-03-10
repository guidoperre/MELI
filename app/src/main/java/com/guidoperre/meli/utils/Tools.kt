package com.guidoperre.meli.utils

class Tools {

    companion object {

        fun parseGoogleResponse(response: String?): List<String> {
            val suggestions = ArrayList<String>()
            if (response != null){
                val split = response.split("[","]")
                if (split.size > 2){
                    val parsed = split[2].split(",")
                    for (suggest in parsed)
                        suggestions.add(suggest.replace("\"",""))
                }
            }
            return suggestions
        }

    }

}