package com.example.hpl_one.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.hpl_one.Modules.Question;

import java.util.ArrayList;
import java.util.List;

import static com.example.hpl_one.Config.*;

public class SQliteQuestionHelper extends SQLiteOpenHelper {
    public SQliteQuestionHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE question (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ques TEXT," +
                "ans_a TEXT," +
                "ans_b TEXT," +
                "ans_c TEXT," +
                "ans_d TEXT," +
                "correct_ans TEXT," +
                "level TEXT" +
                ")";
        db.execSQL(sql);
    }

    public void insertData() {
        SQLiteDatabase st = this.getWritableDatabase();
        List<Question> data = new ArrayList<>();
        data.add(new Question(0, "In many western cultures, the White Lily is a flower that is representative of purity and sweetness\n What is the same meaning with 'representative'?", "advantageous", "marvelous", "symbolic", "universal", "C", "1"));
        data.add(new Question(1, "I can’t figure out Daisy’s attitude toward the trip – she is blowing hot and cold about it. First she’s all excited, but then she seems reluctant to go\n What is the same meaning with 'is blowing hot and cold'?", "keeps talking about trivial things", "keeps going", "keeps changing her mood", "keeps making you confused", "C", "1"));
        data.add(new Question(2, "Social networking aims at promoting and aiding communication. _____", "However", "Otherwise", "Moreover", "As a result", "A", "1"));

        for (Question i: data) {
            String sql = "INSERT INTO " + QUES_TABLE + "(ques, ans_a, ans_b, ans_c, ans_d, correct_ans, level) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String[] args = {i.getQues(), i.getAnswer_a(), i.getAnswer_b(), i.getAnswer_c(), i.getAnswer_d(), i.getCorrect_ans(), i.getLevel()};
            st.execSQL(sql, args);
        }
        st.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + QUES_TABLE);
//        onCreate(db);
    }

    public Question getQues(int id) {
        SQLiteDatabase st = getReadableDatabase();
        Question i = null;
        Cursor rs = st.rawQuery("SELECT * FROM question WHERE id=? LIMIT 1", new String[]{String.valueOf(id)});
        while (rs != null && rs.moveToNext()) {
            int ques_id = rs.getInt(0);
            String ques = rs.getString(1);
            String ans_a = rs.getString(2);
            String ans_b = rs.getString(3);
            String ans_c = rs.getString(4);
            String ans_d = rs.getString(5);
            String correct_ans = rs.getString(6);
            String level = rs.getString(8);
            i = new Question(ques_id, ques, ans_a, ans_b, ans_c, ans_d, correct_ans, level);
        }
        return i;
    }

    public long getQuesAmount() {
        long amount = 0;
        SQLiteDatabase st = getReadableDatabase();
        amount = DatabaseUtils.queryNumEntries(st, QUES_TABLE);
        st.close();
        return amount;
    }
}
