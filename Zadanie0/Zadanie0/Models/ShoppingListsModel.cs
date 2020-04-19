using System;
using System.Collections.Generic;
using System.IO;
using System.Text.Json;
using Zadanie0.DAL.Products;
using Zadanie0.DAL.ShoppingLists;

namespace Zadanie0.Models
{
    public enum SourceType { FILE, DATABASE }
    public class ShoppingListsModel
    {
        //private static int INVALID_INDEX = -1;
        private string SETTINGS_FILE = "settings";
        public List<ShoppingListModel> ShoppingLists { private set; get; }

        private IProductsDAO productsDAO;
        private IShoppingListsDAO shoppingListsDAO;

        private IProductsDAO fileProductsDAO;
        private IProductsDAO sqlProductsDAO;

        private IShoppingListsDAO fileShoppingListsDAO;
        private IShoppingListsDAO sqlShoppingListsDAO;

        public SourceType SourceType { set; get; } 
        private Setting setting;
        public ShoppingListsModel(IShoppingListsDAO fileShoppingListsDAO, IProductsDAO fileProductsDAO,
                                    IShoppingListsDAO sqlShoppingListsDAO, IProductsDAO sqlProductsDAO)
        {
            this.fileProductsDAO = fileProductsDAO;
            this.fileShoppingListsDAO = fileShoppingListsDAO;

            this.sqlShoppingListsDAO = sqlShoppingListsDAO;
            this.sqlProductsDAO = sqlProductsDAO;

            ReadSettings();
            SetDAOs(SourceType);
            ShoppingLists = new List<ShoppingListModel>();
            
        }
        public void SetSourceType(SourceType source)
        {
            SourceType = source;
            setting.Source = source;
            SaveSettings();
            SetDAOs(source);
        }
        public void SetDAOs(SourceType source)
        {
            if (source.Equals(SourceType.FILE))
            {
                productsDAO = fileProductsDAO;
                shoppingListsDAO = fileShoppingListsDAO;
            }
            else
            {
                productsDAO = sqlProductsDAO;
                shoppingListsDAO = sqlShoppingListsDAO;
            }
        }
        public void SynchronizeSources()
        {
            SaveAll();
            var synchronized = new List<ShoppingListModel>();
            var fromFiles = GetData(fileShoppingListsDAO, fileProductsDAO);
            var fromDatabase = GetData(sqlShoppingListsDAO, sqlProductsDAO);
            synchronized.AddRange(fromFiles);
            foreach(var sl in fromDatabase)
            {
                if (synchronized.Exists(list => list.Id == sl.Id))
                {
                    var curr = synchronized.Find(l => l.Id == sl.Id);
                    if(curr.Modified < sl.Modified)
                    {
                        synchronized.Remove(curr);
                        synchronized.Add(sl);
                    }
                }
                else
                {
                    synchronized.Add(sl);
                }
                    
            }
            ShoppingLists = synchronized;
            SaveData(fileShoppingListsDAO, fileProductsDAO);
            SaveData(sqlShoppingListsDAO, sqlProductsDAO);

            

        }
        public void RemoveShoppingList(int id)
        {
            ShoppingLists.RemoveAll(sl => sl.Id == id);
            //RemoveFromStorage(id);
        }

        private void RemoveFromStorage(int id)
        {
            shoppingListsDAO.Delete(id);
        }

        public void AddShoppingList(ShoppingListModel shoppingListModel)
        {
            ShoppingLists.Add(shoppingListModel);
            //Save(shoppingListModel);
        }
        private void Save(ShoppingListModel shoppingListModel)
        {
            shoppingListsDAO.Save(shoppingListModel);
        }
        public void SaveAll()
        {
            ModifiedChanged();
            SaveData(shoppingListsDAO, productsDAO);
            SaveSettings();
        }

        private void ModifiedChanged()
        {
            foreach(var l in ShoppingLists)
            {
                l.Modified = DateTime.Now;
                foreach(var p in l.Items)
                {
                    p.Modified = DateTime.Now;
                }
            }
        }
        private void SaveData(IShoppingListsDAO shoppingListsDAO, IProductsDAO productsDAO)
        {
            productsDAO.DeleteAll();
            shoppingListsDAO.DeleteAll();
            foreach (var sl in ShoppingLists)
            {
                if (sl.Id == null)
                    sl.Id = NewListId();
                shoppingListsDAO.Save(sl);

                foreach (var p in sl.Items)
                {
                    p.ShoppingListId = sl.Id;
                    if (p.Id == null)
                        p.Id = NewProductId();
                    productsDAO.Save(p);
                }

            }
        }

        public void LoadShoppingLists()
        {
            ShoppingLists.Clear();
            ShoppingLists = GetData(shoppingListsDAO, productsDAO);               
        }

        private List<ShoppingListModel> GetData(IShoppingListsDAO shoppingListsDAO, IProductsDAO productsDAO)
        {
            var slists = shoppingListsDAO.Get();
            foreach (var slist in slists)
            {
                
                    var plist = new List<ProductModel>();
                    if (slist.Id != null)
                        plist = productsDAO.GetProductsFromList((int)slist.Id);

                    foreach (var p in plist)
                    {
                        slist.AddItem(p);
                    }
                    //ShoppingLists.Add(slist);

                
            }
            return slists;
        }

        private void SaveSettings()
        {
            var jsonString = JsonSerializer.Serialize(setting);
            File.WriteAllText(SETTINGS_FILE, jsonString);

        }
        private void ReadSettings()
        {
            if (File.Exists(SETTINGS_FILE))
            {
                var jsonString = File.ReadAllText(SETTINGS_FILE);
                setting = JsonSerializer.Deserialize<Setting>(jsonString);
                SourceType = setting.Source;
            }
            else
            {
                setting = new Setting();
                SourceType = SourceType.FILE;
            }
        }
        public int NewListId()
        {
            setting.CurrentListId++;
            return setting.CurrentListId;
        }
        public int NewProductId()
        {
            setting.CurrentProductId++;
            return setting.CurrentProductId;
        }

        [Serializable()]
        private class Setting
        {
            public SourceType Source { set; get; }
            public int CurrentListId { set; get; }
            public int CurrentProductId { set; get; }
            
            
        }
    }
    
}
