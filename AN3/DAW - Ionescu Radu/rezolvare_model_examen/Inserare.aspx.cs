using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Inserare : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void inserare(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            string _Titlul = Titlul.Text;
            string _Comentariu = Comentariu.Text;
            string _Nota = Nota.Text;
            string _Data = Data.Text;
            string _NumeUtilizator = NumeUtilizator.Text;
            string _Film = Film.Text;

            string query = "INSERT INTO recenzii"
                   + "(titlul, comentariu, nota, data, numeutilizator, id_film) VALUES (@titlul, @comentariu, @nota, @data, @numeutilizator, @id_film);"
                   + "SELECT CAST(scope_identity() AS int)";

            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename='D:\FMI\DOC.AN3\Semestrul1\DAW\Examen\Model Examen 2\10. ModelExamen\App_Data\BazaDeDate3.mdf';Integrated Security=True");
            con.Open();

            try
            {
                 // Introducem parametrii in cererea SQL
                    SqlCommand com = new SqlCommand(query, con);
                    com.Parameters.AddWithValue("titlul", _Titlul);
                    com.Parameters.AddWithValue("comentariu", _Comentariu);
                    com.Parameters.AddWithValue("nota", _Nota);
                    com.Parameters.AddWithValue("data", _Data);
                    com.Parameters.AddWithValue("numeutilizator", _NumeUtilizator);
                    com.Parameters.AddWithValue("id_film", _Film);

                    
                    int id_inserare = (int) com.ExecuteScalar(); // Returneaza id-ul ultimei inserari
                    
                   
       
                }
                catch (Exception ex)
                {
                    EroareBazaDate.Text = "Eroare din baza de date: " + ex.Message;
                }
                finally
                {
                   
                    con.Close();
                }
        }
    } 
}