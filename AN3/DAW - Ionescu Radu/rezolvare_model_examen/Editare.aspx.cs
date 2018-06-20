using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Globalization;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Editare : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        // Afisare formular de editare
        if (!Page.IsPostBack && Request.Params["id"] != null)
        {
            // Luam ID-ul
            int ID = int.Parse(Request.Params["id"].ToString());
            // Salvam cererea SQL intr-un string
            string query = "SELECT * "
                    + " FROM recenzii join film on film.id = recenzii.id_film "
                    + " WHERE recenzii.id = @id";

            // Deschidem conexiunea la baza de date
            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename='D:\FMI\DOC.AN3\Semestrul1\DAW\Examen\Model Examen 2\10. ModelExamen\App_Data\BazaDeDate3.mdf';Integrated Security=True");
            con.Open();
           
            try
            {
                // Se construieste comanda SQL
                SqlCommand com = new SqlCommand(query, con);
                com.Parameters.AddWithValue("id", ID);

                // Se executa comanda si se returneaza valorile intr-un reader
                SqlDataReader reader = com.ExecuteReader();

                // Citim rand cu rand din baza de date
                while (reader.Read())
                {
                    Titlul.Text = reader["titlul"].ToString();
                    Comentariu.Text = reader["comentariu"].ToString();
                    Nota.Text = reader["nota"].ToString();
                    Data.Text = DateTime.Parse(reader["data"].ToString()).ToShortDateString();
                    NumeUtilizator.Text = reader["numeutilizator"].ToString();
                    Film.Text = reader["id_film"].ToString();

                }
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

    protected void editare(object sender, EventArgs e)
    {
        if (Page.IsValid && Request.Params["id"] != null)
        {
            int ID = int.Parse(Request.Params["id"].ToString());

            string _Titlul = Titlul.Text;
            string _Comentariu = Comentariu.Text;
            string _Nota = Nota.Text;
            DateTime _Data = DateTime.Parse(Data.Text);
            string _NumeUtilizator = NumeUtilizator.Text;
            string _Film = Film.Text;


            string query = "UPDATE recenzii "
                  + "SET titlul = @titlul, comentariu = @comentariu, nota = @nota, data = @data, numeutilizator = @numeutilizator, id_film = @id_film"
                  + "  WHERE id = @id ";

            SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename='D:\FMI\DOC.AN3\Semestrul1\DAW\Examen\Model Examen 2\10. ModelExamen\App_Data\BazaDeDate3.mdf';Integrated Security=True");

            con.Open();

            try
            {
                // Introducem parametrii in cererea SQL
                SqlCommand com = new SqlCommand(query, con);
                
                com.Parameters.AddWithValue("id", ID);
                com.Parameters.AddWithValue("titlul", _Titlul);
                com.Parameters.AddWithValue("comentariu", _Comentariu);
                com.Parameters.AddWithValue("nota", _Nota);
                com.Parameters.AddWithValue("data", _Data);
                com.Parameters.AddWithValue("numeutilizator", _NumeUtilizator);
                com.Parameters.AddWithValue("id_film", _Film);


                com.ExecuteNonQuery(); 
                

               
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