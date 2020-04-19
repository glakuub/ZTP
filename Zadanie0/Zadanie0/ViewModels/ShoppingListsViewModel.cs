using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using Zadanie0.Models;
using Zadanie0.DAL.ShoppingLists;
using Zadanie0.DAL.Products;

namespace Zadanie0.ViewModels
{
    public class ShoppingListsViewModel: ViewModelBase
    {

        public ObservableCollection<ShoppingListViewModel> ShoppingLists { set; get; } 
        
        private ShoppingListsModel shoppingListsModel;
        private int _selectedIndex = -1;
        public int SelectedList { set { SetProperty(ref _selectedIndex, value); } get { return _selectedIndex; } }
        private ICommand _addList;
        public ICommand AddList { set { SetProperty(ref _addList, value); } get{ return _addList; } }

        public ICommand DeleteList { set; get; }
        public ICommand Save { set; get; }
        public ICommand Synchronize { set; get; }

        private bool _file;
        public bool File { set { shoppingListsModel.SetSourceType(value ? SourceType.FILE:SourceType.DATABASE); _file = value; LoadData(); } get { return _file; } }
        private bool _database;
        public bool Database { set { shoppingListsModel.SetSourceType(value ? SourceType.DATABASE : SourceType.FILE); _database = value; LoadData(); } get { return _database; } }


        public ShoppingListsViewModel(IShoppingListsDAO fileShoppingListsDAO, IProductsDAO fileProductsDAO,
                                      IShoppingListsDAO sqlShoppingListsDAO, IProductsDAO sqlProductsDAO)
        {
            
            ShoppingLists = new ObservableCollection<ShoppingListViewModel>();
            
            shoppingListsModel = new ShoppingListsModel(fileShoppingListsDAO, fileProductsDAO, sqlShoppingListsDAO, sqlProductsDAO);
            if (shoppingListsModel.SourceType.Equals(SourceType.FILE))
            {
                _file = true;
                
            }
            else
            {
                _database = true;
            }
            LoadData();
            DeleteList = new CommandHandler(() => DeleteSelectedList());
            Save = new CommandHandler(() => SaveMethod());
            Synchronize = new CommandHandler(() => SynchronizeMethod());
         

        }
        public void AddEmptyShoppingList()
        {
            var sl = new ShoppingListModel();
            shoppingListsModel.AddShoppingList(sl);
            ShoppingLists.Add(new ShoppingListViewModel(sl));
        }
        
        private void SynchronizeMethod()
        {
            shoppingListsModel.SynchronizeSources();
            LoadData();
        }
        private void SaveMethod()
        {
            shoppingListsModel.SaveAll();
        }
        private void DeleteSelectedList()
        {
            if (_selectedIndex > -1 && _selectedIndex < ShoppingLists.Count())
            {
                var toRemove = ShoppingLists.ElementAt(_selectedIndex);
                shoppingListsModel.RemoveShoppingList(toRemove.Id);
                ShoppingLists.RemoveAt(_selectedIndex);
                UpdataSavedData();
            }
        }
        private void LoadData()
        {
            ShoppingLists.Clear();
            shoppingListsModel.LoadShoppingLists();
            foreach(var slm in shoppingListsModel.ShoppingLists)
            {
                ShoppingLists.Add(new ShoppingListViewModel(slm));
                
            }

           

        }
        private void UpdataSavedData()
        {
            shoppingListsModel.SaveAll();
        }

    }
}
