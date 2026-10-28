import { createRouter, createWebHashHistory } from 'vue-router';
import { useAuth } from '../stores/auth';
import SignIn from '../components/SignIn.vue';
import Settings from '../components/Settings.vue';
import MyDashboard from '../components/MyDashboard.vue';

// Dynamic imports for module components
import MarketDataSnapshots from '../components/MarketDataSnapshots.vue';
import MarketDataSnapshotDetail from '../components/MarketDataSnapshotDetail.vue';
import MarketQuotes from '../components/MarketQuotes.vue';
import MarketQuoteDetail from '../components/MarketQuoteDetail.vue';
import MarketBars from '../components/MarketBars.vue';
import MarketBarDetail from '../components/MarketBarDetail.vue';
import MarketStatuss from '../components/MarketStatuss.vue';
import MarketStatusDetail from '../components/MarketStatusDetail.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard
  },
  
  // Dynamic module routes
  {
    path: '/marketDataSnapshots',
    name: 'MarketDataSnapshots',
    component: MarketDataSnapshots
  },
  {
    path: '/marketDataSnapshot/:id',
    name: 'MarketDataSnapshotDetail',
    component: MarketDataSnapshotDetail,
    props: true
  },
  {
    path: '/marketQuotes',
    name: 'MarketQuotes',
    component: MarketQuotes
  },
  {
    path: '/marketQuote/:id',
    name: 'MarketQuoteDetail',
    component: MarketQuoteDetail,
    props: true
  },
  {
    path: '/marketBars',
    name: 'MarketBars',
    component: MarketBars
  },
  {
    path: '/marketBar/:id',
    name: 'MarketBarDetail',
    component: MarketBarDetail,
    props: true
  },
  {
    path: '/marketStatuss',
    name: 'MarketStatuss',
    component: MarketStatuss
  },
  {
    path: '/marketStatus/:id',
    name: 'MarketStatusDetail',
    component: MarketStatusDetail,
    props: true
  },

  // Core application routes
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
//  {
//    path: '/users',
//    name: 'Users',
//    component: Users,
//    meta: { requiresAuth: true }
//  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 };
  }
});

// Navigation guard for authentication
router.beforeEach((to, from, next) => {
  const auth = useAuth();
  
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    next({ name: 'SignIn', query: { redirect: to.fullPath } });
  } else if (to.name === 'SignIn' && auth.isAuthenticated) {
    next({ name: 'Dashboard' });
  } else {
    next();
  }
});

export default router;
