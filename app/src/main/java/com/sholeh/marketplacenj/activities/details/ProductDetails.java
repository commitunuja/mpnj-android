        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.addTab(tab_layout.newTab().setText("Details"));
        tab_layout.addTab(tab_layout.newTab().setText("Specifications"));
        tab_layout.addTab(tab_layout.newTab().setText("Reviews"));
//        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
//        ViewGroup vg = (ViewGroup) tab_layout.getChildAt(0);
//        int tabsCount = vg.getChildCount();
//        for (int j = 0; j < tabsCount; j++) {
//            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
//            int tabChildsCount = vgTab.getChildCount();
//            for (int i = 0; i < tabChildsCount; i++) {
//                View tabViewChild = vgTab.getChildAt(i);
//                if (tabViewChild instanceof TextView) {
//                    ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
//                }
//            }
//        }

        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);
