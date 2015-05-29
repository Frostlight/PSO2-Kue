package frostlight.pso2kue.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.test.AndroidTestCase;

import frostlight.pso2kue.Utility;

/**
 * TestUtilities
 * Helper functions and constants for TestDb
 * Created by Vincent on 5/29/2015.
 */
public class TestUtilities extends AndroidTestCase{
    static final String TEST_EQ_JAPANESE = "平穏を引き裂く混沌";
    static final String TEST_EQ_ENGLISH = "Chaotic Tranquility";
    static final long TEST_DATE = 1420165680L; // January 1, 2015

    static void verifyValues(SQLiteDatabase sqLiteDatabase, String tableName,
                             ContentValues expectedValues)
    {
        String rawQuery = "SELECT * FROM " + tableName + " WHERE ";
        String whereClause = "";

        // Iterate through each ContentValue key-value pair to generate the WHERE clause
        // of the SQL rawQuery
        for (String key : expectedValues.keySet())
        {
            Object value = expectedValues.get(key);

            if (!whereClause.isEmpty())
                whereClause += " AND ";
            whereClause += key + " = \"" + value.toString() + "\"";
        }

        // Combine the incomplete rawQuery with the whereClause to get the full rawQuery
        rawQuery += whereClause;

        Log.v(Utility.getTag(), "rawQuery: " + rawQuery);
        Cursor cursor = sqLiteDatabase.rawQuery(rawQuery, null);

        // Cursor should not be empty
        assertFalse("Empty cursor returned for query on " + tableName, isCursorEmpty(cursor));
        cursor.close();
    }

    /**
     * Checks if a cursor is empty
     * @param cursor Cursor to check
     * @return True if the cursor is empty, False if the cursor is not empty
     */
    static boolean isCursorEmpty(Cursor cursor)
    {
        return !cursor.moveToFirst() || cursor.getCount() == 0;
    }

    /**
     * Creates a set of test entry values for the calendar table.
     * @return Sample ContentValues for the calendar table
     */
    static ContentValues createCalendarValues () {
        ContentValues calendarValues = new ContentValues();
        calendarValues.put(DbContract.CalendarEntry.COLUMN_EQNAME, TEST_EQ_ENGLISH);
        calendarValues.put(DbContract.CalendarEntry.COLUMN_DATE, TEST_DATE);
        return calendarValues;
    }

    /**
     * Creates a set of test entry values for the Twitter table.
     * @return Sample ContentValues for the Twitter table
     */
    static ContentValues createTwitterValues () {
        ContentValues twitterValues = new ContentValues();
        twitterValues.put(DbContract.TwitterEntry.COLUMN_EQNAME, TEST_EQ_ENGLISH);
        twitterValues.put(DbContract.TwitterEntry.COLUMN_DATE, TEST_DATE);
        return twitterValues;
    }

    /**
     * Creates a set of test entry values for the translation table.
     * @return Sample ContentValues for the translation table
     */
    static ContentValues createTranslationValues () {
        ContentValues translationValues = new ContentValues();
        translationValues.put(DbContract.TranslationEntry.COLUMN_JAPANESE, TEST_EQ_JAPANESE);
        translationValues.put(DbContract.TranslationEntry.COLUMN_ENGLISH, TEST_EQ_ENGLISH);
        return translationValues;
    }
}