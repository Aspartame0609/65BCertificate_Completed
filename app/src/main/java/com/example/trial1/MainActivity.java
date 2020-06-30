package com.example.trial1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private static final int STORAGE_CODE = 1000;
    private EditText name, father_name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mSaveBtn = findViewById(R.id.button);
        name = findViewById(R.id.name);
        father_name = findViewById(R.id.father_name);
        age = findViewById(R.id.age);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions, STORAGE_CODE);
                } else {
                    savePdf();
                }
            }
        });

    }

    private void savePdf() {
        Document mDoc = new Document();

        String mFileName = "Case Number";

        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";

        try {

            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));

            mDoc.open();

            String Name = name.getText().toString();
            String Age = age.getText().toString();
            String Father_Name = father_name.getText().toString();

            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD | Font.UNDERLINE);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 16);
            Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE);

            Chunk c1 = new Chunk("Addendum", font1);
            //mText.setUnderline(0.5f, -2f);

            Chunk c2 = new Chunk("Certificate under Sec. 65B Evidence Act.", font2);

            //String s1 = "I " + Name + " S/o. Sh " + Father_Name + ", Age " + Age + " R/o. ABC Nagar, is a (profession)..... govt. cyber expert/ Police officer/ cyber cafe operator/ private cyber expert/ an advocate, do hereby solemnly declare and affirms as under that-";
            //Chunk c3 = new Chunk(s1, font2);

            Chunk c3_1 = new Chunk("I  ", font2);
            Chunk c3_2 = new Chunk(Name, font3);
            Chunk c3_3 = new Chunk("  S/o. Sh  ", font2);
            Chunk c3_4 = new Chunk(Father_Name, font3);
            Chunk c3_5 = new Chunk(", Age  ", font2);
            Chunk c3_6 = new Chunk(Age, font3);
            Chunk c3_7 = new Chunk("  R/o. ABC Nagar, is a (profession)..... govt. cyber expert/ Police officer/ cyber cafe operator/ private cyber expert/ an advocate, do hereby solemnly declare and affirms as under that-");

            Chunk c4 = new Chunk("   1.   I produced the computer output.. *(Hard copy/ CD/ DVD/Pen Drive etc.) of the Emails/MMS/SMS records/ Whatsapp messenger service records/ call detail records/ Web. Brower records/ CCTV records etc., which represent the link/ communication between the alleged offence/ offender and crime/ victim (in criminal cases) or between the parties (in civil cases). The details of the E-mails/ MMS/ SMS/ Whatsapp massages/ CCTV records/CDR’s etc. are annexed alongwith this certificate as a CD/ DVD/ Pen Drive as Exhibit A---- or at page 1...", font2);

            Chunk c5 = new Chunk("   2.   I further confirm that the computer outputs (E-mails/MMS/SMS records/ Whatsapp messenger service records/ call detail records/ Web. Brower records/ CCTV records etc.) containing the information is/ was produced by computer/s during the period our which the computer/s is/was used regularly to store and process the informations.", font2);


            Paragraph ph = new Paragraph(c1);
            ph.setAlignment(Element.ALIGN_CENTER);

            Paragraph ph2 = new Paragraph(c2);
            ph2.setAlignment(Element.ALIGN_CENTER);

            Paragraph ph3 = new Paragraph();
            ph3.add(c3_1);
            ph3.add(c3_2);
            ph3.add(c3_3);
            ph3.add(c3_4);
            ph3.add(c3_5);
            ph3.add(c3_6);
            ph3.add(c3_7);

            Paragraph ph4 = new Paragraph(c4);

            Paragraph ph5 = new Paragraph(c5);


            mDoc.add(ph);
            mDoc.add(new Paragraph("  "));
            mDoc.add(ph2);
            mDoc.add(new Paragraph("  "));
            mDoc.add(ph3);
            //mDoc.add(new Paragraph("  "));
            mDoc.add(ph4);
            mDoc.add(ph5);


            mDoc.close();

            Toast.makeText(this, mFileName + ".pdf is saved to\n" + mFilePath, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                savePdf();
            } else {
                Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}