package fluxoconsultoria.ufrj.br.surgerynote;

import android.util.Log;

/**
 * Created by ericreis on 6/15/15.
 */
public class DatabaseSingleton
{
    private static DatabaseHelper instance;
    private static final MainActivity mainActivity = new MainActivity();

    private DatabaseSingleton()
    {

    }

    public static DatabaseHelper getInstance()
    {
        if (instance == null)
        {
            instance = new DatabaseHelper(mainActivity.getContext());
        }
        return instance;
    }
}
