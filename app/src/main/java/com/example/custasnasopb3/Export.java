package com.example.custasnasopb3;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import java.util.Calendar;
import java.util.Scanner;

public class Export extends AppCompatActivity {

    //private String nomeDiretorio = "DiretorioTeste";
    private File diretorio;
    private String diretorioApp;
    private String nomeArquivo = "NomeGenerico.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        Listar(findViewById(R.id.arquivosListados));
    }

    public Export() {
    }

    public void ExportarBDLeitura(View view){
        DataBaseHelper dbh = new DataBaseHelper(this);
        CharSequence currentTime = android.text.format.DateFormat.format("-yyyy-MM-dd kk-mm-ss", System.currentTimeMillis());
        CharSequence time = currentTime.toString();
        int count = (int) dbh.contador();
        //Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();

        try {
            for (int i = 1; count >= i; i++){
                StringBuilder sb = new StringBuilder();
                sb.append("---- PAPEL ID" + dbh.getPapel(i).getId() + " ----\n");
                sb.append("- Papel: "+ dbh.getPapel(i).getNomePapel() + "\n");
                sb.append("- Valor: "+ dbh.getPapel(i).getValor() + "\n");
                sb.append("- Quantidade: "+ dbh.getPapel(i).getQuantidade() + "\n");
                sb.append("- Fracionário: "+ dbh.getPapel(i).isFracionario() + "\n");
                sb.append("- TxCorretagem: "+ dbh.getCustas(i).getCorretagem() + " - Fixo: " + dbh.getCustas(i).isCorretagemFixa() + "\n");
                sb.append("- TxCustodia: "+ dbh.getCustas(i).getCustodia() + " - Fixo: " + dbh.getCustas(i).isCustodiaFixa() + "\n");
                sb.append("- TxLiquidação: "+ dbh.getCustas(i).getTx_liquidacao() + " - Fixo: " + dbh.getCustas(i).isTx_liquidacaoFixa() + "\n");
                sb.append("- TxNegociação: "+ dbh.getCustas(i).getTx_negociacao() + " - Fixo: " + dbh.getCustas(i).isTx_negociacaoFixa() + "\n");
                sb.append("- Iss: "+ dbh.getCustas(i).getIss() + " - Fixo: " + dbh.getCustas(i).isIssFixo() + "\n");
                sb.append("--------\n");
                exportIt(sb.toString(), time);
            }

            Toast.makeText(this, "Arquivo para leitura pronto!", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Arquivo: "+ String.valueOf(time), Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ExportarBD(View view){
        DataBaseHelper dbh = new DataBaseHelper(this);
        CharSequence currentTime = android.text.format.DateFormat.format("-yyyy-MM-dd kk-mm-ss", System.currentTimeMillis());
        CharSequence time = "BackUp" + currentTime.toString();
        int count = (int) dbh.contador();
        //Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        //sb.append("ID - ").append("Nome - ").append("Valor - ").append("Quant. - ").append("Fracio. - ").append("Corret. - ").append("CorFix - ").append("Custod - ").append("CustFix - ").append("TxLiq - ").append("TxLiFix - ").append("TxNeg - ").append("TxNeFix - ").append("Iss - ").append("IssFix\n");
        try {
            for (int i = 1; count >= i; i++){
                sb.append(dbh.getPapel(i).getId()).append(" - ").append(dbh.getPapel(i).getNomePapel()).append(" - ").append(dbh.getPapel(i).getValor()).append(" - ").append(dbh.getPapel(i).getQuantidade());
                sb.append(" - ").append(dbh.getCustas(i).getCorretagem()).append(" - ").append(dbh.getCustas(i).isCorretagemFixa()).append(" - ").append(dbh.getCustas(i).getCustodia()).append(" - ").append(dbh.getCustas(i).isCustodiaFixa()).append(" - ").append(dbh.getCustas(i).getTx_liquidacao()).append(" - ").append(dbh.getCustas(i).isTx_liquidacaoFixa()).append(" - ").append(dbh.getCustas(i).getTx_negociacao()).append(" - ").append(dbh.getCustas(i).isTx_negociacaoFixa()).append(" - ").append(dbh.getCustas(i).getIss()).append(" - ").append(dbh.getCustas(i).isIssFixo());
                sb.append(" -\n");
            }
            exportIt(sb.toString(), time);

            Toast.makeText(this, "Arquivo exportado com sucesso!", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Arquivo: "+ String.valueOf(time), Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
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
        //NÃO CONSEGUI ALTERAR UM ARQUIVO CRIADO NESSE DIRETÓRIO, APENAS NO DIRECTORY_DOWNLOADS - diretorioApp = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+nomeDiretorio+"/";

        diretorio = new File(diretorioApp);
        diretorio.mkdirs();

        //nomeArquivo = String.valueOf(((EditText) findViewById(R.id.edSelectedFile)).getText());
        nomeArquivo = (((EditText) findViewById(R.id.edSelectedFile)).getText().toString()) + ".txt";
        File fileExt;
        FileOutputStream fosExt = null;
        FileInputStream fisExt = null;
        FileWriter fw;

        fileExt = new File(diretorioApp, nomeArquivo);
        fileExt.getParentFile().mkdirs();
        try {
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
                            Toast.makeText(this, ("Arquivo localizado: " + f.getName()), Toast.LENGTH_SHORT).show();
                            try {
                                try {
                                    fw = new FileWriter(f, true);
                                    fw.append("\n").append(String.valueOf(((EditText) findViewById(R.id.edListar)).getText())).append("\n");
                                    //char yeahr = textToPrint.charAt(3);
                                    //fw.append(yeahr+"\n");
                                }
                                catch (Exception e){

                                    Toast.makeText(this, "Deu erro ao criar o FileWriter ou no Append: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                    fw = null;
                                    fw.flush();
                                    fw.close();
                                }
                                fw.flush();
                                fw.close();
                            }
                            catch (IOException ioe){
                                ioe.printStackTrace();
                                Toast.makeText(this, ioe.getMessage(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, "IOException", Toast.LENGTH_SHORT).show();
                            }
                            finally {
                                //fw = null;
                                //fw.close();
                                //Quando o FileWriter é setado como Null dá erro pois não é possível fechar um FileWriter null, e ele acusa que a instância fw não foi iniciada.
                                Toast.makeText(this, "Finally ativado", Toast.LENGTH_SHORT).show();
                            }

                            Toast.makeText(this, "Arquivo existente escrito!", Toast.LENGTH_SHORT).show();
                            fExistente =+ 1;
                        }
                    }
                }
                if (fExistente == 0){
                    Toast.makeText(this, "Arquivo não localizado", Toast.LENGTH_SHORT).show();
                    fosExt = new FileOutputStream(fileExt);
                    fosExt.write(((EditText)findViewById(R.id.edListar)).getText().toString().getBytes());
                    Toast.makeText(this, "Novo arquivo criado!", Toast.LENGTH_SHORT).show();
                    fosExt.close();
                }
            }
            else {
                Toast.makeText(this, "Pasta vazia!", Toast.LENGTH_SHORT).show();
                fosExt = new FileOutputStream(fileExt);
                fosExt.write(((EditText)findViewById(R.id.edListar)).getText().toString().getBytes());
                Toast.makeText(this, "Novo arquivo criado!", Toast.LENGTH_SHORT).show();
                fosExt.close();
            }
            Listar(findViewById(R.id.arquivosListados));
        }
        catch (Exception e){
            Toast.makeText(this, "Não foi possível escrever", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            //((EditText)findViewById(R.id.edListar)).setText(e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void exportIt(String data, CharSequence time){
        diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        if (time == null){
            time = android.text.format.DateFormat.format("-yyyy-MM-dd kk-mm", System.currentTimeMillis());
        }
        nomeArquivo = "CustasB3BD"+ time +".txt";
        diretorio = new File(diretorioApp);
        diretorio.mkdirs();

        File fLog;
        FileWriter fw = null;

        fLog = new File(diretorioApp, nomeArquivo);
        try {
            fLog.getParentFile().mkdirs();
        }
        catch (NullPointerException npe){
            npe.printStackTrace();
        }

        try {
            fw = new FileWriter(fLog, true);
            fw.append(android.text.format.DateFormat.format("yyyy-MM-dd kk:mm:ss-", System.currentTimeMillis()));
            fw.append(data + "\n");
            fw.flush();
            fw.close();
        }
        catch (Exception e){
            e.printStackTrace();
            if (fw != null){
                try {
                    fw.close();
                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
        finally {
            if (fw != null){
                try {
                    fw.flush();
                    fw.close();
                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
    }

    public void loggIt(String data){
        diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        nomeArquivo = "CustasB3Log.txt";
        diretorio = new File(diretorioApp);
        diretorio.mkdirs();


        File fLog;
        FileOutputStream fosLog;
        FileWriter fw = null;

        fLog = new File(diretorioApp, nomeArquivo);
        try {
            fLog.getParentFile().mkdirs();
        }
        catch (NullPointerException npe){
            npe.printStackTrace();
        }

        try {
            fw = new FileWriter(fLog, true);
            fw.append(android.text.format.DateFormat.format("yyyy-MM-dd kk:mm:ss - ", System.currentTimeMillis()));
            fw.append(data + "\n");
            fw.flush();
            fw.close();
        }
        catch (Exception e){
            e.printStackTrace();
            if (fw != null){
                try {
                    fw.close();
                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
        finally {
            if (fw != null){
                try {
                    fw.flush();
                    fw.close();
                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
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
        diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

        diretorio = new File(diretorioApp);
        diretorio.mkdirs();
        Spinner spList = new Spinner(this);
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
            //((TextView)findViewById(R.id.edListar)).setText("Not yet");

            arq = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), lstrNomeArq);
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null){
                //((TextView)findViewById(R.id.edListar)).append("\n");
                ((TextView)findViewById(R.id.edListar)).append(lstrlinha);
            }
            Toast.makeText(this, "Texto carregado com sucesso!", Toast.LENGTH_SHORT).show();
            br.close();
        }
        catch (Exception e){
            Toast.makeText(this, (e.getMessage()), Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.edListar)).setText("Not happening..");
        }
    }

    public void carregar2(View v){
        String lstrNomeArq;
        File arq;
        String lstrlinha;
        int caracteres, offCharReader, offSequencia, skip;
        try {
            caracteres = Integer.parseInt(((EditText)findViewById(R.id.edCharAt)).getText().toString());
            offCharReader = Integer.parseInt(((EditText)findViewById(R.id.edOffCharReader)).getText().toString());
            offSequencia = Integer.parseInt(((EditText)findViewById(R.id.edOffSequencia)).getText().toString());
            skip = Integer.parseInt(((EditText)findViewById(R.id.edSkip)).getText().toString());
        }
        catch (Exception e) {
            ((EditText)findViewById(R.id.edCharAt)).setText(String.valueOf(1000));
            ((EditText)findViewById(R.id.edOffCharReader)).setText(String.valueOf(0));
            ((EditText)findViewById(R.id.edOffSequencia)).setText(String.valueOf(20));
            ((EditText)findViewById(R.id.edSkip)).setText(String.valueOf(128));

            caracteres = Integer.parseInt(((EditText)findViewById(R.id.edCharAt)).getText().toString());
            offCharReader = Integer.parseInt(((EditText)findViewById(R.id.edOffCharReader)).getText().toString());
            offSequencia = Integer.parseInt(((EditText)findViewById(R.id.edOffSequencia)).getText().toString());
            skip = Integer.parseInt(((EditText)findViewById(R.id.edSkip)).getText().toString());
        }

        try {
            lstrNomeArq = ((EditText) findViewById(R.id.edSelectedFile)).getText().toString();

            arq = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), lstrNomeArq);
            BufferedReader br = new BufferedReader(new FileReader(arq));


            char[] chars = new char[8000];
            String sequencia;
            String charAtt;
            int count = 0;

            int charReader = br.read(chars, offCharReader, caracteres);

            if (charReader != -1){
                sequencia = new String(chars, offSequencia, charReader);
                char charAt;
                ((EditText)findViewById(R.id.edCharAt)).setText(String.valueOf(chars.length));
                System.out.println(chars.length);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i<sequencia.length(); i++){
                    charAt = sequencia.charAt(i);
                    charAtt = String.valueOf(charAt);
                    if (!charAtt.equals("-")){
                        if (!charAtt.equals(" ")){
                            if (!charAtt.equals("\n")){
                                sb.append(charAtt);
                            }
                        }
                    }
                    else {
                        //((TextView)findViewById(R.id.txtRecebeDoArquivo)).append(sb.toString());
                        //((TextView)findViewById(R.id.txtRecebeDoArquivo)).append("\n");
                        count++;
                        switch (count){
                            case 0:
                                System.out.println("----- START! -----");
                                break;
                            case 1:
                                String id = sb.toString();
                                System.out.println("ID: " + id);
                                ((EditText)findViewById(R.id.edID)).setText(id);
                                break;
                            case 2:
                                String nome = sb.toString();
                                System.out.println("Nome: " + nome);
                                ((EditText)findViewById(R.id.edNome)).setText(sb.toString());
                                break;
                            case 3:
                                String valor = sb.toString();
                                System.out.println("Valor: " + valor);
                                ((EditText)findViewById(R.id.edValor)).setText(sb.toString());
                                break;
                            case 4:
                                String quantidade = sb.toString();
                                System.out.println("Quantidade: " + quantidade);
                                ((EditText)findViewById(R.id.edQuantidade)).setText(sb.toString());
                                break;
                            case 5:
                                System.out.println("Corretagem: " + sb.toString());
                                break;
                            case 6:
                                System.out.println("Corretagem fixa: " + sb.toString());
                                break;
                            case 7:
                                System.out.println("Custodia: " + sb.toString());
                                break;
                            case 8:
                                System.out.println("Custodia fixa: " + sb.toString());
                                break;
                            case 9:
                                System.out.println("Tx de Liquidação: " + sb.toString());
                                break;
                            case 10:
                                System.out.println("Tx de Liquidação fixa: " + sb.toString());
                                break;
                            case 11:
                                System.out.println("Tx de Negociação: " + sb.toString());
                                break;
                            case 12:
                                System.out.println("Tx de Negociação fixa: " + sb.toString());
                                break;
                            case 13:
                                System.out.println("ISS: " + sb.toString());
                                break;
                            case 14:
                                System.out.println("ISS fixo: " + sb.toString());
                                break;
                        }
                        sb = new StringBuilder();
                        if (count == 14){
                            count = 0;
                            System.out.println("RESTART COUNT: " + count);
                        }
                    }
                }
            }
            else {
                charAtt = "Tcharannn!";
                sequencia = "";
            }
            br.close();
            Toast.makeText(this, "Texto carregado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            ((TextView)findViewById(R.id.txtRecebeDoArquivo)).setText("Not happening..");
        }
    }
}
