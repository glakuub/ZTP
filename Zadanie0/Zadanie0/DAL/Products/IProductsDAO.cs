using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Zadanie0.Models;

namespace Zadanie0.DAL.Products
{
    public interface IProductsDAO
    {
         List<ProductModel> GetProductsFromList(int id);
         int Save(ProductModel productDTO);
        void DeleteAll();
    }
}
