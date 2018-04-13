package osp.edu.pl.shopingassistant;

import android.provider.BaseColumns;

public final class GoodsListDataContract {
        private GoodsListDataContract() {}

        public static class GoodsListData implements BaseColumns {
            public static final String TABLE_NAME = "goodslist";
            public static final String COLUMN_NAME_NAME = "title";
        }

}
