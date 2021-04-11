package lv.rtu.tic_tac_toe

class Player {
    private var playerName:String
    private val playerSymbol:Char
    constructor(playerName:String, playerSymbol:Char){
        this.playerName = playerName
        this.playerSymbol = playerSymbol
    }
    fun getPlayerSymbol():Char{ return playerSymbol}
    fun getPlayerName():String{ return playerName}
    fun setPlayerName(name:String?){
        if(name!="" && name != null) playerName = name
    }


}