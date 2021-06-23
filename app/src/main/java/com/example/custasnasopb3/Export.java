package com.example.custasnasopb3;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.Scanner;

public class Export extends AppCompatActivity {

    private String nomeDiretorio = "DiretorioTeste";
    private File diretorio;
    private String diretorioApp;
    private String nomeArquivo = "OlhaoBreguecoFuncionando.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        Listar(findViewById(R.id.arquivosListados));
    }



    public void Exportar(Context context) {

        /*String input = "1 fish 2 fish red fish blue fish";
        Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");*/
        //Scanner sc = new Scanner();
        /*File file = new File(Environment.getExternalStoragePublicDirectory("/") + "/testes/TextoFiles.txt");
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write("string".getBytes());
            outputStream.close();
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);

            //getApplicationContext() retorna o contexto da aplicação, não sendo necessário definir
            MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, null);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {

        }*/
        /*StringBuilder sb = new StringBuilder();
        sb.append(sc.nextInt());
        sb.append(sc.nextInt());
        sb.append(sc.next());
        sb.append(sc.next());

        Toast.makeText(context, sb, Toast.LENGTH_SHORT).show();*/

        //sc.close();
    }
    public void testeCanWrite(View v){
        //AQUI CONSEGUE ESCREVER!!!!  File temp = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath());

        diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        //diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        //Environment.getExternalStorageDirectory().canWrite();


        //File temp = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath());
        File temp = new File(diretorioApp);
        String canwrite;
        if (temp.canWrite()){
            canwrite = "yes!!";
        }
        else {
            canwrite = "NO!";
        }


        /*if (Environment.getExternalStorageDirectory().canWrite()){
            canwrite = "yes!!";
        }
        else {
            canwrite = "NO!";
        }*/
        Toast.makeText(this, canwrite, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, temp.toString(), Toast.LENGTH_SHORT).show();
    }

    public void criarArquivo(View v){

        diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        //diretorioApp = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+nomeDiretorio+"/";
//
//        CÓDIGO ABAIXO CRIADO PARA TESTAR SE ERA POSSÍVEL ESCREVER
//        File temp = new File(diretorioApp);
//        String canwrite;
//        if (temp.canWrite()){
//            canwrite = "yes!!";
//        }
//        else {
//            canwrite = "NO!";
//        }
//        Toast.makeText(this, canwrite, Toast.LENGTH_SHORT).show();


        diretorio = new File(diretorioApp);
        diretorio.mkdirs();

        //nomeArquivo = String.valueOf(((EditText) findViewById(R.id.edSelectedFile)).getText());
        nomeArquivo = (((EditText) findViewById(R.id.edSelectedFile)).getText().toString()) + ".txt";
        //Toast.makeText(this, nomeArquivo, Toast.LENGTH_SHORT).show();
        File fileExt;
        FileOutputStream fosExt = null;
        FileInputStream fisExt = null;
        FileWriter fw;

        fileExt = new File(diretorioApp, nomeArquivo);
        fileExt.getParentFile().mkdirs();

//        JÁ DEU MERDA LOGO DE INICIO MESMO! KKK
//        try{
//            fw = new FileWriter(diretorioApp, true);
//            fw.append("\n");
//        }
//        catch (IOException io){
//            io.printStackTrace();
//            Toast.makeText(this, "Deu merda logo no inicio!", Toast.LENGTH_SHORT).show();
//        }

        //Fiz para testar, ainda sem resultados.
//        try {
//            //save("whatnow", "NovoArquivo.txt");
//
//            fosExt = openFileOutput(nomeArquivo, Context.MODE_APPEND);
//            fosExt.write("whatnow".getBytes());
//            fosExt.flush();
//            fosExt.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }



        /*if(diretorioApp.contentEquals(nomeArquivo)){
            Toast.makeText(this, "Primeiro teste - diretorioApp contem o arquivo!", Toast.LENGTH_SHORT).show();
            fileExt = new File(diretorioApp);
        }
        else {
            fileExt = new File(diretorioApp, nomeArquivo);
            fileExt.getParentFile().mkdirs();
            Toast.makeText(this, "Primeiro teste deu negativo", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, diretorioApp.toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, nomeArquivo.toString(), Toast.LENGTH_SHORT).show();
        }*/



        //FileOutputStream fosExt = null;
        /*try {
            fosExt = new FileOutputStream(fileExt);
        }
        catch (Exception e){
            Toast.makeText(this, "Arquivo não encontrado", Toast.LENGTH_SHORT).show();
        }*/







        try {
            /*if (((((EditText) findViewById(R.id.edSelectedFile)).getText().toString())).equals(fileExt.getName() + ".txt")){
                fosExt = openFileOutput(nomeArquivo, MODE_APPEND);
                fosExt.write(((EditText)findViewById(R.id.edListar)).getText().toString().getBytes());

                Toast.makeText(this, "Arquivo existente!", Toast.LENGTH_SHORT).show();
            }
            else {
                //fosExt.write(((EditText)findViewById(R.id.edListar)).getText().toString().getBytes());
                Toast.makeText(this, "Arquivo NÃO existente!", Toast.LENGTH_SHORT).show();
            }*/

            File[] listarArquivos = diretorio.listFiles();
            if (listarArquivos != null){
                int fExistente = 0;
                for (int i = 0; i< listarArquivos.length; i++){
                    File f = listarArquivos[i];
                    if (f.isFile()){
                        if (f.getName().equals(nomeArquivo)){
                            f.setExecutable(true);
                            f.setReadable(true);
                            f.setWritable(true);
                            Toast.makeText(this, ("Achamos um arquivo: " + f.getName()), Toast.LENGTH_SHORT).show();
                            try {
                                Toast.makeText(this, "FW início", Toast.LENGTH_SHORT).show();
                                String canwrite2;
                                if (f.canWrite()){
                                    canwrite2 = "f CAN WRITE!!";
                                    if (f.getName()==null){
                                        Toast.makeText(this, "F.getName é null!!", Toast.LENGTH_SHORT).show();
                                    }
                                    else {

                                        Toast.makeText(this, "F.getName NÃO é null!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    canwrite2 = "f não pode ser gravado!";
                                }
                                Toast.makeText(this, canwrite2, Toast.LENGTH_SHORT).show();
                                try {
                                    fw = new FileWriter(f.getName(), true);
                                    //Attempt to invoke virtual method 'java.io.Writer java.io.FileWriter.append(char)' on a null object reference
                                    //TENTAR GERAR DIRETAMENTE O ARQUIVO E REALIZAR O APPEND NO INICIO DO CÓDIGO
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                    fw = new FileWriter("LOG_DE_ERRO", true);
                                    fw.append(e.getMessage());
                                    fw.flush();
                                    fw.close();
                                }
                                String yeah = "owyear!";
                                char yeahr = yeah.charAt(1);
                                Toast.makeText(this, "FW meio", Toast.LENGTH_SHORT).show();
                                fw.append(yeahr);
                                fw.append(yeahr);
                                fw.append(yeahr);
//                                fw.append("GOD BLESS IT!");
//                                fw.append("GOD BLESS IT!");
//                                fw.append("GOD BLESS IT!");
                                Toast.makeText(this, "FW fim", Toast.LENGTH_SHORT).show();
                                fw.flush();
                                fw.close();

                                Toast.makeText(this, "FW testado", Toast.LENGTH_SHORT).show();

                                /*fosExt = openFileOutput(f.getName(), Context.MODE_APPEND); //Já tentei incluindo o .txt no final
                                fosExt.write((((EditText)findViewById(R.id.edListar)).getText().toString()).getBytes());
                                fosExt.flush();
                                fosExt.close();*/




//                                Não funciona
//                                openFileOutput(f.getName(), MODE_APPEND).write((((EditText)findViewById(R.id.edListar)).getText().toString()).getBytes());

//                                Não funciona
//                                fosExt = new FileOutputStream(f.getName(), true);
//                                byte[] edListar = ((EditText)findViewById(R.id.edListar)).getText().toString().getBytes();
//                                fosExt.write(edListar);

//                                Não funciona
//                                fosExt = new FileOutputStream(f.getName(), true);
//                                fosExt.write((((EditText)findViewById(R.id.edListar)).getText().toString()).getBytes());


                            }
                            catch (IOException ioe){
                                ioe.printStackTrace();
                                Toast.makeText(this, ioe.getMessage(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, "IOException", Toast.LENGTH_SHORT).show();
                            }
                            //fw.append(((EditText)findViewById(R.id.edListar)).getText().toString());
                            //fosExt.write(("\n").getBytes());
                            //Toast.makeText(this, ((EditText)findViewById(R.id.edListar)).getText().toString(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(this, (((EditText)findViewById(R.id.edListar)).getText().toString().getBytes()).toString(), Toast.LENGTH_SHORT).show();
                            //fosExt.write(((EditText)findViewById(R.id.edListar)).getText().toString().getBytes());
                            //fosExt.write(mybytes);

                            Toast.makeText(this, "Arquivo existente escrito!", Toast.LENGTH_SHORT).show();
                            fExistente =+ 1;
                        }
                        else {
                            Toast.makeText(this, i +" - fExistente = "+ fExistente, Toast.LENGTH_SHORT).show();
                        }
                        /*((TextView)findViewById(R.id.txtaArquivosListados)).append("\n");
                        ((TextView)findViewById(R.id.txtaArquivosListados)).setText(f.getName());*/
                        //Toast.makeText(this, (f.getName()), Toast.LENGTH_SHORT).show();
                    }
                }
                if (fExistente == 0){
                    //Toast.makeText(this, "If fora do array!", Toast.LENGTH_SHORT).show();
                    fosExt = new FileOutputStream(fileExt);
                    fosExt.write(((EditText)findViewById(R.id.edListar)).getText().toString().getBytes());
                    Toast.makeText(this, "Novo arquivo escrito!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Pasta vazia!", Toast.LENGTH_SHORT).show();
                fosExt = new FileOutputStream(fileExt);
                fosExt.write(((EditText)findViewById(R.id.edListar)).getText().toString().getBytes());
                Toast.makeText(this, "Novo arquivo criado!", Toast.LENGTH_SHORT).show();
            }

            /*fosExt = new FileOutputStream(fileExt);
            fosExt.write(((EditText)findViewById(R.id.edListar)).getText().toString().getBytes());
            Toast.makeText(this, "Escrivinhado!", Toast.LENGTH_SHORT).show();
            fosExt.close();*/
            Listar(findViewById(R.id.arquivosListados));
        }
        catch (Exception e){
            Toast.makeText(this, "Não foi possível escrever", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            ((EditText)findViewById(R.id.edListar)).setText(e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        try {
            fosExt.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Não foi possível fechar", Toast.LENGTH_SHORT).show();
        }

//        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + nomeDiretorio + "/", "RecebaEssetxt");
//        FileOutputStream fileOutputStream;
//
//        try {
//            fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write("string".getBytes());
//            fileOutputStream.close();
//            file.setExecutable(true);
//            file.setReadable(true);
//            file.setWritable(true);
//
//            MediaScannerConnection.scanFile(this, new String[]{file.getAbsolutePath()}, null, null);
//        }
//        catch (Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
    }



    private void save(String data, String fileName){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = getApplicationContext().openFileOutput(fileName, Context.MODE_APPEND);
            byte[] bytes = data.getBytes();
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (writer!=null){writer.close();}
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void Listar(View v){
        LinearLayout LL = findViewById(R.id.arquivosListados);
        LL.setOrientation(LinearLayout.VERTICAL);
        diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/"+nomeDiretorio+"/";

        diretorio = new File(diretorioApp);
        diretorio.mkdirs();
        try {
            File[] listarArquivos = diretorio.listFiles();
            if (listarArquivos != null){
                //Toast.makeText(this, "Array criado!", Toast.LENGTH_SHORT).show();
                LL.removeAllViews();
                for (int i = 0; i< listarArquivos.length; i++){
                    File f = listarArquivos[i];
                    if (f.isFile()){
                        TextView tv = new TextView(this);
                        tv.setText(f.getName());
                        LL.addView(tv);
                    }
                }
            }
        }
        catch (Exception e){
            TextView tv = new TextView(this);
            tv.setText("Pasta vazia");
            LL.addView(tv);
        }
    }

    /*private String Diretorio(){
        File root = android.os.Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        Toast.makeText(this, (root.toString()), Toast.LENGTH_SHORT).show();
        return root.toString();
    }*/

    /*public void Listar(View v){

        ((TextView)findViewById(R.id.edListar)).append("\nListar OK");

        File diretorio = new File(Diretorio());
        File[] arquivos = diretorio.listFiles();
        if (arquivos != null){
            //int length = arquivos.length;
            for (int i = 0; i < arquivos.length; ++i){
                File f = arquivos[i];
                if (f.isFile()){
                    Toast.makeText(this, f.getName(), Toast.LENGTH_SHORT).show();
                    ((TextView)findViewById(R.id.edListar)).append("\n");
                    ((TextView)findViewById(R.id.edListar)).setText(f.getName());
                }
            }
        }
        else {
            ((EditText)findViewById(R.id.edListar)).setText("Sem arquivos para mostrar");
        }
    }*/
    public void salvarTxt(View v){
        String lstrNomeArq;
        File arq;
        byte[] dados;
        try {
            lstrNomeArq = ((EditText) findViewById(R.id.edSelectedFile)).getText().toString();
            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
            FileOutputStream fos;

            dados = ((TextView) findViewById(R.id.txtListaCoin)).getText().toString().getBytes();
            //dados = lstrNomeArq.getBytes();

            fos = new FileOutputStream(arq);
            fos.write(dados);
            fos.flush();
            fos.close();
            Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, (e.getMessage()), Toast.LENGTH_SHORT).show();
        }
    }
    public void carregar(View v){
        String lstrNomeArq;
        File arq;
        String lstrlinha;
        try {
            lstrNomeArq = ((EditText) findViewById(R.id.edSelectedFile)).getText().toString();
            ((TextView)findViewById(R.id.edListar)).setText("Not yet");

            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null){
                if (!((TextView)findViewById(R.id.edListar)).getText().toString().equals("")){
                    ((TextView)findViewById(R.id.edListar)).append("\n");
                    ((TextView)findViewById(R.id.edListar)).append(lstrlinha);
                }
            }
            Toast.makeText(this, "Texto carregado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, (e.getMessage()), Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.edListar)).setText("Not happening..");
        }
    }
}
