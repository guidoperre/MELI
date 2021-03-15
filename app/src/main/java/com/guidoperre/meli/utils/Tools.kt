package com.guidoperre.meli.utils

import com.guidoperre.meli.entities.search.Search

class Tools {

    companion object {

        /*
        La API de Google devuelve una respuesta sin formato y aca se tratan los datos
        para obtener la respuesta esperada
        */
        fun parseGoogleResponse(response: String?): List<Search> {
            val suggestions = ArrayList<Search>()
            if (response != null){
                val split = response.split("[","]")
                if (split.size > 2){
                    val parsed = split[2].split(",")
                    for (suggest in parsed)
                        suggestions.add(
                            Search(
                                0L,
                                false,
                                suggest.replace("\"","")
                            )
                        )
                }
                if (suggestions.size == 1 && suggestions[0].name == "")
                    suggestions.clear()
            }
            return suggestions
        }

    }

}