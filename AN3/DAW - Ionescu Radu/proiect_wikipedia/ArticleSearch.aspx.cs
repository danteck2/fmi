using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class ArticleSearch : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!Page.IsPostBack && Request.Params["q"] != null)
        {
            string query = Server.UrlDecode(Request.Params["q"]);

            SDSSearch.SelectCommand = "SELECT Article.Id, Article.Title, Article.Text, Article.Date, Category.CategoryName FROM Category INNER JOIN Article ON Category.Id = Article.IdCategory" + " WHERE Title LIKE @q OR Text LIKE @q";

            SDSSearch.SelectParameters.Clear();
            SDSSearch.SelectParameters.Add("q","%" + query + "%");
            SDSSearch.DataBind();
        }
    }
}