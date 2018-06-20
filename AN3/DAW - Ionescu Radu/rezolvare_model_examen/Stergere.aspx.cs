using System;
using System.Collections;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Stergere : System.Web.UI.Page
{
    // Se defineste dataArray public pentru a fi available si in frontend
    public ArrayList dataArray;

    protected void Page_Load(object sender, EventArgs e)
    {
        // Deschidem conexiunea la baza de date
        SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename='D:\FMI\DOC.AN3\Semestrul1\DAW\Examen\Model Examen 2\10. ModelExamen\App_Data\BazaDeDate3.mdf';Integrated Security=True");
        con.Open();

        // Daca nu este selectat departamentul
        if ((Request.Params["recenzii"] == null))
        {
            string query = "SELECT DISTINCT titilul FROM recenzii";
            SqlCommand com = new SqlCommand(query, con);
            // Se executa comanda si se returneaza valorile intr-un reader
            SqlDataReader reader = com.ExecuteReader();
            // Scoatem datele intr-un array
            dataArray = new ArrayList();
            // Citim rand cu rand din baza de date
            while (reader.Read())
            {
                // Se genereaza un obiect pentru stocarea valorilor din reader (reader.FieldCount reprezinta numarul de randuri din tabel)
                Object[] values = new Object[reader.FieldCount];
                // Scoatem valorile din fiecare rand al bazei de date in obiectul instantiat mai sus
                reader.GetValues(values);
                // Inseram valorile scoase mai devreme in dataArray pentru a le putea procesa in frontend
                dataArray.Add(values);
            }

            reader.Close();
            
        }

        // Alegem datele utilizatorilor dintr-un departament
        if (Request.Params["recenzii"] != null)
        {
            string query = "SELECT * from recenzii join film on film.id = recenzii.id_angajat WHERE recenzii.titlul = @titlul";
            SqlCommand com = new SqlCommand(query, con);
            // Adaugam denumirea departamentului la query
            com.Parameters.AddWithValue("titlul", Request.Params["titlul"]);
            // Se executa comanda si se returneaza valorile intr-un reader
            SqlDataReader reader = com.ExecuteReader();
            // Scoatem datele intr-un array
            dataArray = new ArrayList();
            // Citim rand cu rand din baza de date
            while (reader.Read())
            {
                // Se genereaza un obiect pentru stocarea valorilor din reader (reader.FieldCount reprezinta numarul de randuri din tabel)
                Object[] values = new Object[reader.FieldCount];
                // Scoatem valorile din fiecare rand al bazei de date in obiectul instantiat mai sus
                reader.GetValues(values);
                // Inseram valorile scoase mai devreme in dataArray pentru a le putea procesa in frontend
                dataArray.Add(values);
            }
        }

        // Un utilizator a fost ales pentru stergere
        if (Request.Params["user_id"] != null)
        {
            string QueryStergereSalarii = "DELETE FROM salarii WHERE id_angajat = @id_angajat";
            string QueryStergereDepartament = "DELETE FROM departament WHERE id_angajat = @id_angajat";
            string QueryStergereUser = "DELETE FROM angajat WHERE id = @id_angajat";

            SqlCommand com1 = new SqlCommand(QueryStergereSalarii, con);
            SqlCommand com2 = new SqlCommand(QueryStergereDepartament, con);
            SqlCommand com3 = new SqlCommand(QueryStergereUser, con);

            com1.Parameters.AddWithValue("id_angajat", Request.Params["user_id"]);
            com2.Parameters.AddWithValue("id_angajat", Request.Params["user_id"]);
            com3.Parameters.AddWithValue("id_angajat", Request.Params["user_id"]);

            try
            {
                com1.ExecuteNonQuery();
                com2.ExecuteNonQuery();
                com3.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                Response.Write(ex.Message);
            }
            finally
            {

                con.Close();
            }

        }

        // Inchidem conexiunea la baza de date
        con.Close();
    }
}