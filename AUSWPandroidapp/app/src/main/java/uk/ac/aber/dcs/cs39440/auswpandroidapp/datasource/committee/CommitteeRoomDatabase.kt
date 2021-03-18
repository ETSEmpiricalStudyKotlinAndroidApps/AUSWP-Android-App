package uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.committee

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.committee.Committee
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.committee.CommitteeDAO

@Database(entities = [Committee::class], version = 1)


abstract class CommitteeRoomDatabase: RoomDatabase() {

    abstract fun committeeDAO(): CommitteeDAO

    companion object {
        private var instance:CommitteeRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.Main)

        fun getDatabase(context:Context):CommitteeRoomDatabase?{
            synchronized(this){
                if(instance == null){
                    instance =
                        Room.databaseBuilder<CommitteeRoomDatabase>(
                            context.applicationContext,
                            CommitteeRoomDatabase::class.java,
                            "Committee_Database"
                        )
                            .allowMainThreadQueries()
                            .addCallback(roomDatabaseCallback(context))
                            //.addMigration(MIGRATION_1_2, MIGRATION_2_3
                            .build()
                }
                return instance!!
            }
        }

        private fun roomDatabaseCallback(context: Context):Callback{
            return object : Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    coroutineScope.launch(Dispatchers.IO){
                        populatedDatabase(context, getDatabase(context)!!)
                    }
                }
            }
        }

        val MIGRATION_1_2 = object:Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("Migrate", "Doing a migrate from V 1.0 to V 2.0")
            }
        }
        private fun populatedDatabase(context: Context, instance: CommitteeRoomDatabase){

            val President = Committee(
                0,
                "Joe",
                "President",
            "jod66@aber.ac.uk",
                "Joe1.png"
            )

            val Treasurer = Committee(
                0,
                "Cal",
                "Treasurer",
                "crb20@aber.ac.uk",
                "cal1.png"
            )

            val MWPC = Committee(
                0,
                "Isaac",
                "Men's Water Polo Captain",
                "isd8@aber.ac.uk",
                "Isaac1.png"
            )

            val WWPC = Committee(
                0,
                "Line",
            "Woman's Water Polo Captain",
            "lim41@aber.ac.uk",
                "Line1.png"
            )

            val SwimCap = Committee(
                0,
                "Rachel",
                "Swim Captain",
            "raj32@aber.ac.uk",
                "Rachel1.png"
            )

            val SwimCoach = Committee(
                0,
                "Rebecca",
                "Swim Coach",
                "rhr18@aber.ac.uk",
                "Rebecca1.png"
            )

            val socsec1 = Committee(
                0,
                "Heidi",
                "Social Sec",
            "heb35@aber.ac.uk",
                "Heidi1.png"
            )

            val socsec2 = Committee(
                0,
                "Miles",
                "Social Sec",
                "mid25@aber.ac.uk",
                "Miles1.png"
            )

            val committeeList = mutableListOf(
                President,
                Treasurer,
                MWPC,
                WWPC,
                SwimCap,
                SwimCoach,
                socsec1,
                socsec2
            )

            val dao = instance.committeeDAO()
            dao.insertMultipleCommittee(committeeList)
        }
    }


}