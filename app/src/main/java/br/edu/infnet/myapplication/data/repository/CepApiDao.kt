package br.edu.infnet.myapplication.data.repository

import br.edu.infnet.myapplication.data.models.Endereco
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CepApiDao {

    //https://viacep.com.br/
    @GET("ws/{cep}/json")
    suspend fun getEncedereco (
        @Path("cep") cep: String
    ) : Endereco

}