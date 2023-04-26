package com.example.thuchanh2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.thuchanh2.model.ChuyenNganh;
import com.example.thuchanh2.model.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

public class KhoaHocDAO extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "CoVanAnhXinhGai.db";
    private final static int DATABSE_VERSION = 1;
    private final static String TABLE_NAME = "khoa_hoc";

    public KhoaHocDAO(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                     + "(id integer primary key autoincrement, ten text, "
                     + "chuyenNganh text, ngayBatDau date, hocPhi text, kichHoat int)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(KhoaHoc khoaHoc) {
        String sql = "INSERT INTO " + TABLE_NAME
                     + "(ten, chuyenNganh, ngayBatDau, hocPhi, kichHoat) "
                     + "VALUES(?, ?, ?, ?, ?)";
        String[] args = {
                khoaHoc.getTen(),
                khoaHoc.getChuyenNganh().name(),
                khoaHoc.getNgayBatDau(),
                khoaHoc.getHocPhi(),
                Integer.toString(khoaHoc.getKichHoat()),
                };

        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public List<KhoaHoc> serializeResult(Cursor cursor) {
        List<KhoaHoc> khoaHocList = new ArrayList<>();

        while (cursor != null && cursor.moveToNext()) {
            khoaHocList.add(
                    new KhoaHoc(
                            cursor.getInt(0),
                            cursor.getString(1),
                            ChuyenNganh.valueOf(cursor.getString(2)),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getInt(5)
                    )
            );
        }

        cursor.close();
        return khoaHocList;
    }

    public List<KhoaHoc> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        return this.serializeResult(cursor);
    }

    public int update(KhoaHoc khoaHoc) {
        ContentValues values = new ContentValues();
        values.put("ten", khoaHoc.getTen());
        values.put("chuyenNganh", khoaHoc.getChuyenNganh().name());
        values.put("ngayBatDau", khoaHoc.getNgayBatDau());
        values.put("hocPhi", khoaHoc.getHocPhi());
        values.put("kichHoat", String.valueOf(khoaHoc.getKichHoat()));

        String where = "id = ?";
        String[] whereAgrs = {Integer.toString(khoaHoc.getMa())};

        SQLiteDatabase db = getWritableDatabase();
        return db.update(TABLE_NAME, values, where, whereAgrs);
    }

    public int delete(int ma) {
        String where = "id = ?";
        String[] whereArgs = {Integer.toString(ma)};

        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public List<KhoaHoc> search(String keyword, int kichHoat) {
        String sql = "select * from " + TABLE_NAME
                + " where ten LIKE ? AND kichHoat = ?";
        String keywordLike = "%" + keyword + "%";
        String[] whereArgs = {keywordLike, Integer.toString(kichHoat)};

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, whereArgs);

        return this.serializeResult(cursor);
    }
}
