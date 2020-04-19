using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Zadanie0.Models;

namespace Zadanie0.DAL.ShoppingLists
{
    public interface IShoppingListsDAO
    {
        List<ShoppingListModel> Get();
        int Save(ShoppingListModel shoppingListDTO);
        void DeleteAll();
        void Delete(int id);
    }
}
