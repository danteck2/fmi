using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class AddArticle : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void AddButton_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            try
            {
                int IDCategory = int.Parse(DDLCategory.SelectedValue);
                string title = TBTitle.Text;
                string text = TBText.Text;
                DateTime publicationDate = DateTime.Parse(TBDate.Text);

                string query = "INSERT INTO Article (IdCategory, Title, Text, Date)"
                    + " VALUES (@id_category, @title, @text, @p_date)";

                SqlConnection con = new SqlConnection(@"Data Source=(localdb)\MSSQLLocalDB;AttachDbFilename='C:\Users\Dan\Desktop\DOC.AN3\Semestrul1\DAW\Laboratoare DAW\6.Cars\App_Data\Articles.mdf';Initial Catalog=Articles;Integrated Security=True");

                con.Open();

                try
                {
                    SqlCommand com = new SqlCommand(query, con);
                    com.Parameters.AddWithValue("id_category", IDCategory);
                    com.Parameters.AddWithValue("title", title);
                    com.Parameters.AddWithValue("text", text);
                    com.Parameters.AddWithValue("p_date", publicationDate);

                    com.ExecuteNonQuery();

                    // Do this only if insert works:
                    LAnswer.Text = "New car added successfully!";

                    DDLCategory.SelectedIndex = 0;
                    TBTitle.Text = "";
                    TBText.Text = "";
                    TBDate.Text = "";
                }
                catch (Exception ex)
                {
                    LAnswer.Text = "Database insert error : " + ex.Message;
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