using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class DeleteArticle : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Page.IsPostBack == false && Request.Params["id"] != null)
        {
            try
            {
                int ID = int.Parse(Request.Params["id"].ToString());

                string query = "SELECT CategoryName, Title" + " FROM Article INNER JOIN Category ON Article.IdCategory = Category.Id" + " WHERE Article.Id = @id";

                SqlConnection con = new SqlConnection(@"Data Source=(localdb)\MSSQLLocalDB;AttachDbFilename='C:\Users\Dan\Desktop\DOC.AN3\Semestrul1\DAW\Laboratoare DAW\6.Cars\App_Data\Articles.mdf';Initial Catalog=Articles;Integrated Security=True");

                con.Open();

                try
                {
                    SqlCommand com = new SqlCommand(query, con);
                    com.Parameters.AddWithValue("id", ID);

                    SqlDataReader reader = com.ExecuteReader();

                    while (reader.Read())
                    {
                        LCategory.Text = reader["CategoryName"].ToString();
                        LTitle.Text = reader["Title"].ToString();
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

    protected void DeleteButton_Click(object sender, EventArgs e)
    {
        if (Page.IsValid && Request.Params["id"] != null)
        {
            try
            {
                int ID = int.Parse(Request.Params["id"].ToString());

                string query = "DELETE FROM Article WHERE Id = @id";

                SqlConnection con = new SqlConnection(@"Data Source=(localdb)\MSSQLLocalDB;AttachDbFilename='C:\Users\Dan\Desktop\DOC.AN3\Semestrul1\DAW\Laboratoare DAW\6.Cars\App_Data\Articles.mdf';Initial Catalog=Articles;Integrated Security=True");

                con.Open();

                try
                {
                    SqlCommand com = new SqlCommand(query, con);
                    com.Parameters.AddWithValue("id", ID);

                    com.ExecuteNonQuery();

                    PConfirm.Visible = false;
                    HLHomepage.Visible = true;
                    LAnswer.Text = "Article deleted successfully!";
                }
                catch (Exception ex)
                {
                    LAnswer.Text = "Database delete error : " + ex.Message;
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

    protected void CancelButton_Click(object sender, EventArgs e)
    {
        Response.Redirect("Index.aspx");
    }
}