package practice10

sealed class FireType {

    object singleShot : FireType()

    data class burstShot(val burstSize: Int) : FireType()
}
