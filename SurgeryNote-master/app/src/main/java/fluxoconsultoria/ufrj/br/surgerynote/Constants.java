package fluxoconsultoria.ufrj.br.surgerynote;

import android.widget.Toast;

/**
 * Created by ericreis on 6/11/15.
 */
public class Constants
{
    public static final String NAME_REGEX = "^[a-zA-Z\\s]+";
    public static final String PROFESSIONAL_ID_REGEX = "[0-9]+";
    public static final String SPECIALITY_REGEX = "^[a-zA-Z\\s]+";
    public static final String EMAIL_REGEX = "[a-zA-Z0-9._-@\\.]";

    public static final String EMAIL_VALIDATION = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                                                  "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final int YEAR_OFFSET = 1900;
}
