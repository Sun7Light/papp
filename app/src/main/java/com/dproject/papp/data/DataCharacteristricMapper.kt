package com.dproject.papp.data

import com.dproject.papp.domain.characteristic.CharacteristicEntity
import com.dproject.papp.domain.characteristic.Positivity

object DataCharacteristricMapper {
    fun toCharacteristicEntity(dataCharacteristicEntity: DataCharacteristicEntity): CharacteristicEntity {
        var positivity:Positivity = Positivity.negative

        if(dataCharacteristicEntity.positivity == 0)
            positivity = Positivity.neutral
        else if(dataCharacteristicEntity.positivity > 0)
            positivity = Positivity.positiv

        return CharacteristicEntity(dataCharacteristicEntity.id, dataCharacteristicEntity.name, positivity, dataCharacteristicEntity.personalValueGroupId)
    }

    fun toDataCharacteristicEntity(characteristicEntity: CharacteristicEntity): DataCharacteristicEntity {
        var positivity: Int = -1

        if(characteristicEntity.positivity == Positivity.positiv)
            positivity = 1
        else if(characteristicEntity.positivity == Positivity.neutral)
            positivity = 0

        return DataCharacteristicEntity(characteristicEntity.id, characteristicEntity.name, positivity, characteristicEntity.personalValueGroupId)
    }
}