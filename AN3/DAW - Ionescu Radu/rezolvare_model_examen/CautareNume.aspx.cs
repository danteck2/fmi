using System;
using System.Collections;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Cautare_dupa_nume : System.Web.UI.Page
{
    // Se defineste dataArray public pentru a fi available si in frontend
    public ArrayList dataArray = new ArrayList();

    protected void Page_Load(object sender, EventArgs e)
    {
        // Deschidem conexiunea la baza de date
        SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename='D:\FMI\DOC.AN3\Semestrul1\DAW\Examen\Model Examen 2\10. ModelExamen\App_Data\BazaDeDate2.mdf';Integrated Security=True");
        con.Open();

      
        if ((Request.Params["search"] != null))
        {
            string query = "SELECT * FROM angajat WHERE nume like @nume OR prenume like @prenume";
            SqlCommand com = new SqlCommand(query, con);
            com.Parameters.AddWithValue("nume", "%" + Request.Params["search"] + "%");
            com.Parameters.AddWithValue("prenume", "%" + Request.Params["search"] + "%");

            // Se executa comanda si se returneaza valorile intr-un reader
            SqlDataReader reader = com.ExecuteReader();
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

    }
}