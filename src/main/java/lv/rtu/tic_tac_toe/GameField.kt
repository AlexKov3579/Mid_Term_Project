package lv.rtu.tic_tac_toe

import kotlin.random.Random

class GameField {

    private var field:Array<Array<String>>
    private var availableSpace:Int = 9
//    for easier PvC implementation
    private var fieldList: MutableList<Int>

    constructor(){
        fieldList = MutableList(9){ index: Int ->index*1 }
        val row1 = arrayOf("", "", "")
        val row2 = arrayOf("", "", "")
        val row3 = arrayOf("", "", "")
        field = arrayOf<Array<String>>(row1, row2, row3)
    }

    fun setVal(row:Int, col:Int, player:Player):Boolean{
        if(field[col][row] !="") return false
        field[col][row] = player.getPlayerSymbol().toString()

        fieldList.remove(col*3+row)
        availableSpace--
        return true
    }
    fun setRandomVal(player:Player):String{

        val rand = fieldList[Random.nextInt(0, fieldList.size)]
        val col:Int = rand/3
        val row:Int = rand%3
        System.out.println("\nChoosen elem : $rand for setVal ${col*3+row}")
        System.out.println("col $col \n row $row \n --------")
        setVal(col, row, player)
        for(elem in fieldList) System.out.print("Element in list : $elem |")
        return "$col$row"
    }


    fun checkForDraw():Boolean{
        if(availableSpace<1) return true
        return false
    }


    fun checkForWin(player: Player) : Boolean{
        val symbol : String =  player.getPlayerSymbol().toString()
        for(row in field){
            if(row[0]==row[1] && row[0]==row[2] && row[0] == symbol) return true
        }
        for(i in 0..2){
            if(field[0][i]==field[1][i] && field[0][i]==field[2][i] && field[0][i]==symbol) return true
        }
        if (field[0][0]==field[1][1] && field[0][0]==field[2][2] && field[0][0]== symbol) return true
        if (field[0][2]==field[1][1] && field[0][2]==field[2][0] && field[0][2]== symbol) return true

        return false
    }


}