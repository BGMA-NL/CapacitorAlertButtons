import { registerPlugin } from '@capacitor/core';

import type { AlertButtonsPlugin } from './definitions';

const AlertButtons = registerPlugin<AlertButtonsPlugin>('AlertButtons', {
  web: () => import('./web').then((m) => new m.AlertButtonsWeb()),
});

export * from './definitions';
export { AlertButtons };
