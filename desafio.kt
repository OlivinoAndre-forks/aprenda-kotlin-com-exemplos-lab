// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)
import java.time.LocalDateTime
​
object SimpleLog {
    fun info(msg: String) = println("${LocalDateTime.now()} $msg")
}
​
enum class Nivel(peso: Int) { BASICO(0), INTERMEDIARIO(1), AVANCADO(2) }
​
data class Usuario(var nome: String, var email:String){
    constructor(): this("","")
}
​
data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel) {
    init {
        SimpleLog.info("curso $nome criado.")
    }
}
​
data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {
​
    val inscritos = mutableListOf<Usuario>()
    val nivel: Nivel = conteudos.maxBy{it.nivel}.nivel
    
    init {
        SimpleLog.info("formação $nome criada com nivel $nivel.")
    }
    fun matricular(vararg usuarios: Usuario) {
        for (usuario in usuarios) {
           inscritos.add(usuario)
        }
    }
}
​
fun main() {
    
    val curso_1 = ConteudoEducacional(
        nome = "curso 1",
        duracao = 120,
        nivel = Nivel.INTERMEDIARIO
    )
    
    val curso_2 = ConteudoEducacional(
        nome = "curso 2",
        duracao = 60,
        nivel = Nivel.BASICO
    )
    
    val curso_3 = ConteudoEducacional(
        nome = "curso 3",
        duracao = 60,
        nivel = Nivel.AVANCADO
    )
    
    val formacao_1 = Formacao(
        nome = "formacao 1",
        conteudos = listOf(curso_1, curso_2)
    )
    
    val formacao_2 = Formacao(
        nome = "formacao 2",
        conteudos = listOf(curso_2, curso_3)
    )
    
    val formacao_3 = Formacao(
        nome = "formacao 3",
        conteudos = listOf(curso_1,curso_2, curso_3)
    )
    
    val usuario_1 = Usuario().apply{
        nome = "usuario 1"
        email = "usuario1@user.com"
    }
    
    val usuario_2 = Usuario(
        nome = "usuario 2",
        email = "usuario2@user.com"
    )
     
    val usuario_3 = Usuario().also{
        it.nome = "usuario 3"
        it.email = "usuario3@user.com"
    }
    
    formacao_1.matricular(usuario_1)
    formacao_2.matricular(usuario_1)
    formacao_2.matricular(usuario_2)
    formacao_3.matricular(usuario_1,usuario_2,usuario_3)
    
    formacao_1.let{
        println("${it.nome} tem ${it.inscritos.count()} inscritos")
    }
    
    formacao_2.run{
        println("${nome} tem ${inscritos.count()} inscritos")
    }
    
    with(formacao_3){
        println("${nome} tem ${inscritos.count()} inscritos")
    }
}
