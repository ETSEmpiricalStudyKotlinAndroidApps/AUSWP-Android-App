/**
 * Represents the committee as a room entity.
 * @author Callum Robert Binner
 * @version 1
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.model.committee

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "committee")
data class Committee (
        @PrimaryKey(autoGenerate = true)
        @NonNull
        var id:Int =0,
        var name:String,
        var position:String,
        var email: String,
        @ColumnInfo(name = "main_image_path")
        var imagePath: String ="")
