
enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class User(val nome: String, val email: String, val senha: String)

data class ConteudoEducacional(val nome: String, val duracaoEmMinutos: Int = 95, val nivel: Nivel)

class Formacao(
    val nome: String,
    val nivel: Nivel,
    val conteudos: List<ConteudoEducacional>
) {
    private val inscritos = mutableSetOf<User>()

    fun matricular(vararg users: User) {
        for (user in users) {
            if (inscritos.add(user)) {
                println("${user.nome} foi matriculado na formação $nome.")
            } else {
                println("${user.nome} já está matriculado na formação $nome.")
            }
        }
    }

    fun getInscritos(): Set<User> {
        return inscritos.toSet()
    }

    fun duracaoTotalDoCursoEmHoras(): Double {
        return conteudos.sumBy { it.duracaoEmMinutos } / 60.0
    }

    fun duracaoDosModulosEmHoras(): List<Double> {
        return conteudos.map { it.duracaoEmMinutos / 60.0 }
    }
}

fun main() {
    val modulo1 = ConteudoEducacional("Prepare-se Para a Jornada (Onboarding)", 120, Nivel.BASICO)
    val modulo2 = ConteudoEducacional("Princípios de Desenvolvimento de Software", 360, Nivel.BASICO)
    val modulo3 = ConteudoEducacional("Conhecendo a Linguagem de Programação Kotlin", 780, Nivel.BASICO)
    val modulo4 = ConteudoEducacional("Resolvendo Seus Primeiros Desafios de Código", 360, Nivel.BASICO)
    val modulo5 = ConteudoEducacional("Introdução ao Desenvolvimento Mobile com Kotlin", 1260, Nivel.BASICO)
    val modulo6 = ConteudoEducacional("Praticando Sua Abstração no Domínio Bancário", 300, Nivel.INTERMEDIARIO)
    val modulo7 = ConteudoEducacional("Ganhando Produtividade com o Android Jetpack", 1500, Nivel.AVANCADO)

    val formacao = Formacao(
        "Santander Bootcamp 2023 - Mobile Android com Kotlin",
        Nivel.AVANCADO,
        listOf(modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, modulo7)
    )

    val user1 = User("Thiago", "thiago88@mail.com", "5588")
    val user2 = User("Paula", "pa5@mail.com", "5164")
    val user3 = User("Fernanda", "fe4@mail.com", "8584")

    formacao.matricular(user1, user2, user3)

    println("Inscritos na formação ${formacao.nome}: ${formacao.getInscritos().joinToString(", ") { it.nome }}")
    println("Duração total do curso: ${formacao.duracaoTotalDoCursoEmHoras()} horas")
    formacao.conteudos.forEachIndexed { index, conteudo ->
        println("Módulo ${index + 1}: ${conteudo.nome} - Duração: ${conteudo.duracaoEmMinutos / 60.0} horas")
    }
}