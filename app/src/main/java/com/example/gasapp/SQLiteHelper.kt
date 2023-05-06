package com.example.anew

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "oil.db"
        private const val TBL_OIL = "tbl_oil"
        private const val ID  = "id"
        private const val DATE = "nd"
        private const val LITERS = "un"
        private const val TOTAL = "tot"


    }
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTblElectricity = ("CREATE TABLE " + TBL_OIL + "("
                + ID + " INTEGER PRIMARY KEY," + DATE + " INTEGER," + LITERS + " INTEGER," + TOTAL + " DOUBLE" + ")")

        p0?.execSQL(createTblOil)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TBL_OIL")
        onCreate(p0)
    }

    fun insertBill(elc: OilModel): Long{
        val p0 = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, elc.id)
        contentValues.put(DATE, elc.nd)
        contentValues.put(LITERS, elc.un)
        contentValues.put(TOTAL, elc.tot)

        val success = p0.insert(TBL_OIL, null, contentValues)
        p0.close()
        return success
    }


    @SuppressLint("Range")
    fun getBill(): ArrayList<OilModel>{
        val elcList: ArrayList<OilModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_OIL"
        val p0 = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = p0.rawQuery(selectQuery, null)
        } catch(e: Exception){
            e.printStackTrace()
            p0.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var nd: Int
        var un: Int
        var tot: Double

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                nd = cursor.getInt(cursor.getColumnIndex("nd"))
                un = cursor.getInt(cursor.getColumnIndex("un"))
                tot = cursor.getDouble(cursor.getColumnIndex("tot"))

                val elc = OilModel(id = id, nd = nd, un = un, tot = tot)
                elcList.add(elc)
            } while (cursor.moveToNext())
        }

        return elcList
    }

    fun updateBill(elc: OilModel) : Int{
        val p0 = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, elc.id)
        contentValues.put(DATE, elc.nd)
        contentValues.put(LITERS, elc.un)
        contentValues.put(TOTAL, elc.tot)

        val success = p0.update(TBL_OIL, contentValues,"id=" + elc.id, null)
        p0.close()
        return success
    }

    fun deleteBillById(id:Int): Int{
        val p0 = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = p0.delete(TBL_OIL, "id=$id", null)
        p0.close()
        return success
    }
}






















