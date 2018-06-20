<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="UpdateProfile.aspx.cs" Inherits="UpdateProfile" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <asp:LoginView ID="LVProfile" runat="server">
        <AnonymousTemplate>
            This page has restricted access.
            Return to <asp:HyperLink ID="HyperLink1" NavigateUrl="~/Index.aspx" runat="server">My Home page</asp:HyperLink> or sign in.
        </AnonymousTemplate>
        <LoggedInTemplate>
            <div style="padding: 10px">
                <asp:Label ID="Label2" runat="server" Text="First Name:"></asp:Label>
                <br />
                <asp:TextBox ID="TBFirstName" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" ControlToValidate="TBFirstName" runat="server" ErrorMessage="First name is required" ForeColor="Red"></asp:RequiredFieldValidator>
            </div>
            <div style="padding: 10px">
                <asp:Label ID="Label6" runat="server" Text="Last Name:"></asp:Label>
                <br />
                <asp:TextBox ID="TBLastName" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator5" ControlToValidate="TBLastName" runat="server" ErrorMessage="Last name is required" ForeColor="Red"></asp:RequiredFieldValidator>
            </div>
            <div style="padding: 10px">
                <asp:Label ID="Label7" runat="server" Text="Birthday:"></asp:Label>
                <br />
                <asp:TextBox ID="TBBirthday" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator6" ControlToValidate="TBBirthday" runat="server" ErrorMessage="Birthday is required" ForeColor="Red" Display="Dynamic"></asp:RequiredFieldValidator>
                <asp:CompareValidator ID="CompareValidator1" runat="server" ControlToValidate="TBBirthday" ErrorMessage="Date must be in MM/DD/YYYY format" ForeColor="Red" Type="Date" Operator="DataTypeCheck" Display="Dynamic"></asp:CompareValidator>
            </div>
            <div style="padding: 10px">
                <asp:Label ID="Label3" runat="server" Text="Gender:"></asp:Label>
                <br />
                <div style="float: left; width: 120px">
                <asp:RadioButtonList ID="RBLGender" runat="server" RepeatDirection="Horizontal">
                    <asp:ListItem Text="Female" Value="0"></asp:ListItem>
                    <asp:ListItem Text="Male" Value="1"></asp:ListItem>
                </asp:RadioButtonList>
                </div>
                <div style="float: right; width: 315px">
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" ControlToValidate="RBLGender" runat="server" ErrorMessage="Gender is required" ForeColor="Red"></asp:RequiredFieldValidator>    
                </div>
            </div>
            <div style="padding: 50px">
                <asp:Button ID="SaveButton" runat="server" Text="Save Profile" OnClick="SaveButton_Click" />
                <br />
                <asp:Literal ID="LMessage" runat="server"></asp:Literal>
            </div>
        </LoggedInTemplate>
        <RoleGroups>
            <asp:RoleGroup Roles="Admin">
                <ContentTemplate>
                    <div style="padding: 10px">
                        <asp:Label ID="Label2" runat="server" Text="First Name:"></asp:Label>
                        <br />
                        <asp:TextBox ID="TBFirstName" runat="server"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator1" ControlToValidate="TBFirstName" runat="server" ErrorMessage="First name is required" ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>
                    <div style="padding: 10px">
                        <asp:Label ID="Label6" runat="server" Text="Last Name:"></asp:Label>
                        <br />
                        <asp:TextBox ID="TBLastName" runat="server"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator5" ControlToValidate="TBLastName" runat="server" ErrorMessage="Last name is required" ForeColor="Red"></asp:RequiredFieldValidator>
                    </div>
                    <div style="padding: 10px">
                        <asp:Label ID="Label7" runat="server" Text="Birthday:"></asp:Label>
                        <br />
                        <asp:TextBox ID="TBBirthday" runat="server"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator6" ControlToValidate="TBBirthday" runat="server" ErrorMessage="Birthday is required" ForeColor="Red" Display="Dynamic"></asp:RequiredFieldValidator>
                        <asp:CompareValidator ID="CompareValidator1" runat="server" ControlToValidate="TBBirthday" ErrorMessage="Date must be in MM/DD/YYYY format" ForeColor="Red" Type="Date" Operator="DataTypeCheck" Display="Dynamic"></asp:CompareValidator>
                    </div>
                    <div style="padding: 10px">
                        <asp:Label ID="Label3" runat="server" Text="Gender:"></asp:Label>
                        <br />
                        <div style="float: left; width: 120px">
                        <asp:RadioButtonList ID="RBLGender" runat="server" RepeatDirection="Horizontal">
                            <asp:ListItem Text="Female" Value="0"></asp:ListItem>
                            <asp:ListItem Text="Male" Value="1"></asp:ListItem>
                        </asp:RadioButtonList>
                        </div>
                        <div style="float: right; width: 315px">
                        <asp:RequiredFieldValidator ID="RequiredFieldValidator2" ControlToValidate="RBLGender" runat="server" ErrorMessage="Gender is required" ForeColor="Red"></asp:RequiredFieldValidator>    
                        </div>
                    </div>
                    <div style="padding: 50px">
                        <asp:Button ID="SaveButton" runat="server" Text="Save Profile" OnClick="SaveButton_Click" />
                        <br />
                        <asp:Literal ID="LMessage" runat="server"></asp:Literal>
                    </div>
                    <asp:HyperLink ID="HyperLink2" runat="server" NavigateUrl="~/AssigningRoles.aspx">AssigningRoles</asp:HyperLink>
                </ContentTemplate>
            </asp:RoleGroup>
        </RoleGroups>
    </asp:LoginView>

                 

</asp:Content>

