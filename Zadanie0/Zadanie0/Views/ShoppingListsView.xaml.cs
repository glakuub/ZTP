using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Zadanie0.DAL.Products;
using Zadanie0.DAL.ShoppingLists;
using Zadanie0.ViewModels;

namespace Zadanie0.Views
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class ShoppingListsView : Window
    {
        private string cs = @"Server=jakubgladysz.com;Database=ztp_zadanie0_2;User Id=ztp_user;Password=ztp_pass";
        //readonly ViewModels.ShoppingListsViewModel vm;
        public ShoppingListsView()
        {
            DataContext = new ViewModels.ShoppingListsViewModel( new FileShoppingListsDAO(), new FileProductsDAO(), new SQLShoppingListsDAO(cs), new SQLProductsDAO(cs))
            {
                AddList = new CommandHandler(() => (DataContext as ShoppingListsViewModel).AddEmptyShoppingList())
            };


            InitializeComponent();
        }
    }
}
