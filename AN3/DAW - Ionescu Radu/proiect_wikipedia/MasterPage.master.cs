using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class MasterPage : System.Web.UI.MasterPage
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void BSearch_Click(object sender, EventArgs e)
    {
        Response.Redirect("ArticleSearch.aspx?q=" + Server.UrlEncode(TBSearch.Text));
    }
}
