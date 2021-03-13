package child.wellness.app;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class Java2Kotlin<firstStart> {

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String DefaultUnameValue = "";
    private String UnameValue;

    private final String DefaultPasswordValue = "";
    private String PasswordValue;

    SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
    boolean firstStart = prefs.getBoolean("firstStart", true);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        @Override
        public void onPause() {
            super.onPause();
            savePreferences();

        }

        @Override
        public void onResume() {
            super.onResume();
            loadPreferences();
        }

        private void loadPreferences() {

            SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);

            // Get value
            UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
            PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
            edt_username.setText(UnameValue);
            edt_password.setText(PasswordValue);
            System.out.println("onResume load name: " + UnameValue);
            System.out.println("onResume load password: " + PasswordValue);
        }

        private void savePreferences() {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();

            // Edit and commit
            UnameValue = edt_username.getText();
            PasswordValue = edt_password.getText();
            System.out.println("onPause save name: " + UnameValue);
            System.out.println("onPause save password: " + PasswordValue);
            editor.putString(PREF_UNAME, UnameValue);
            editor.putString(PREF_PASSWORD, PasswordValue);
            editor.commit();
        }

        private void savePreferences() {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();

            // Edit and commit
            UnameValue = edt_username.getText();
            PasswordValue = edt_password.getText();
            System.out.println("onPause save name: " + UnameValue);
            System.out.println("onPause save password: " + PasswordValue);
            editor.putString(PREF_UNAME, UnameValue);
            editor.putString(PREF_PASSWORD, PasswordValue);
            editor.commit();
        }

        private void loadPreferences() {

            SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);

            // Get value
            UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
            PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
            edt_username.setText(UnameValue);
            edt_password.setText(PasswordValue);
            System.out.println("onResume load name: " + UnameValue);
            System.out.println("onResume load password: " + PasswordValue);
        }

        if (firstStart) {

            String password = (generateString(12));
            private String generateString ( int length);
        }
        {
            char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789".toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                char c = chars[random.nextInt(chars.length)];
                stringBuilder.append(c);
            }
            return stringBuilder.toString();
        }

        private void showStartDialog () {
            new AlertDialog.Builder(this)
                    .setTitle("Welcome to CWA!")
                    .setMessage("Generated admin password")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

            SharedPrefrences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart", false)
        }

    }

}
