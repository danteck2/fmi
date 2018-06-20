using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class UpdateArticle : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Page.IsPostBack == false && Request.Params["id"] != null)
        {
            try
            {
                int ID = int.Parse(Request.Params["id"].ToString());

                string query = "SELECT IdCategory, Title, Text, Date" 
                    + " FROM Article"
                    + " WHERE Id = @id";

                SqlConnection con = new SqlConnection(@"Data Source=(localdb)\MSSQLLocalDB;AttachDbFilename='C:\Users\Dan\Desktop\DOC.AN3\Semestrul1\DAW\Laboratoare DAW\6.Cars\App_Data\Articles.mdf';Initial Catalog=Articles;Integrated Security=True");

                con.Open();

                try
                {
                    SqlCommand com = new SqlCommand(query, con);
                    com.Parameters.AddWithValue("id", ID);

                    SqlDataReader reader = com.ExecuteReader();

                    while (reader.Read())
                    {
                        DDLCategory.SelectedValue = reader["IdCategory"].ToString();
                        TBTitle.Text = reader["Title"].ToString();
                        TBText.Text = reader["Text"].ToString();
                        TBDate.Text = DateTime.Parse(reader["Date"].ToString()).ToShortDateString();
                    }
                }
                catch (Exception ex)
                {
                    LAnswer.Text = "Database select error : " + ex.Message;
                }
                finally
                {
                    con.Close();
                }
            }
            catch (Exception se)
            {
                LAnswer.Text = "Database connexion error : " + se.Message;
            }
        }
    }

    protected void SaveButton_Click(object sender, EventArgs e)
    {
        if (Page.IsValid && Request.Params["id"] != null)
        {
            try
            {
                int ID = int.Parse(Request.Params["id"].ToString());

                int IDCategory = int.Parse(DDLCategory.SelectedValue);
                string title = TBTitle.Text;
                string text = TBText.Text;
                DateTime publicationDate = DateTime.Parse(TBDate.Text);

                string query = "UPDATE Article SET IdCategory = @id_category, Title = @title, Text = @text, Date = @f_date"
                    + " WHERE Id = @id";

                SqlConnection con = new SqlConnection(@"Data Source=(localdb)\MSSQLLocalDB;AttachDbFilename='C:\Users\Dan\Desktop\DOC.AN3\Semestrul1\DAW\Laboratoare DAW\6.Cars\App_Data\Articles.mdf';Initial Catalog=Articles;Integrated Security=True");

                con.Open();

                try
                {
                    SqlCommand com = new SqlCommand(query, con);
                    com.Parameters.AddWithValue("id_category", IDCategory);
                    com.Parameters.AddWithValue("title", title);
                    com.Parameters.AddWithValue("text", text);
                    com.Parameters.AddWithValue("f_date", publicationDate);
                    com.Parameters.AddWithValue("id", ID);

                    com.ExecuteNonQuery();
                    LAnswer.Text = "Article saved successfully!";
                }
                catch (Exception ex)
                {
                    LAnswer.Text = "Database update error : " + ex.Message;
                }
                finally
                {
                    con.Close();
                }
            }
            catch (SqlException se)
            {
                LAnswer.Text = "Database connexion error : " + se.Message;
            }
            catch (Exception ex)
            {
                LAnswer.Text = "Data conversion error : " + ex.Message;
            }
        }
    }
}