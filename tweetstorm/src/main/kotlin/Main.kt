import services.Splitter
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val splitter = Splitter()

    println("Bem vindo ao tweetstorm, seu textão em pílulas de 140 carácters! O limite de caracteres é 2720, o " +
            "equivalente a 20 tweets. \n")

    when {
        args.size != 1 -> println("Inclua uma e apenas uma mensagem delimitada por aspas duplas").also { exitProcess(0) }
        args[0].length > 2720 -> println("Texto acima do tamanho permitido").also { exitProcess(0) }
        args[0].length <= 140 -> println("Sua mensagem cabe em um tweet!")
        else -> splitter.splitString(args[0])

    }

    println("\n Obrigado por utilizar o Tweetstorm.")
}