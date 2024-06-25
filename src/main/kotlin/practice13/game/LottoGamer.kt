package practice13.game

import practice13.game.base.IGamer
import practice13.table.cell.impl.DrawableCell
import practice13.table.table.LottoTable

class LottoGamer(
    val name: String,
    val cards: List<LottoTable>
) : IGamer<Int> {

    override fun makeMove(data: Int) {
        cards.forEach { card ->

            val cellInCard = card.findCell(data)

            if (cellInCard != null) {
                card.updateCell(cellInCard) { cell ->
                    val newCell = DrawableCell(
                        id = cellInCard.getId(),
                        format = cellInCard.getFormat(),
                        value = data,
                        coordinate = cellInCard.getCoordinate()
                    )
                    newCell.paintOver()
                    newCell
                }
                println("\n===================================================================================")
                println(" Игрок $name отмечает в одном из ${cards.count()} своих билетов число ${data} ")
                println("\t\t\t\t\tБилет ")
                println(card)
            }
        }
    }

    override fun isWin(): Boolean {
        return cards.any { card ->
            var isWin = false

            for (i in 0 until card.settings.rowsCount) {
                val cells = card.getRow(i)
                val paintedInRow = cells.filter { it?.isPainted() == true }.count()
                if (paintedInRow == card.settings.winCountInRow) {
                    isWin = true
                    printWinInfo(card)
                    break
                }
            }
            isWin
        }
    }


    private fun printWinInfo(card: LottoTable) {
        println("""
            
                            ПОБЕДА
                 Поздравляем победителя! $name
                        Лотерейный билет
            
        """.trimIndent()
        )
        println(card)
    }

}