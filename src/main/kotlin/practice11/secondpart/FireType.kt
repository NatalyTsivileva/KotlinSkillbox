package practice11.secondpart

sealed class FireType {

    object singleShot : FireType()

    data class burstShot(val burstSize: Int) : FireType()
}
