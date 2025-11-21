# capacitor-alert-buttons

Native alert buttons without message and title

## Install

```bash
npm install @bgma-nl/capacitor-alert-buttons
npx cap sync
```

## API

<docgen-index>

* [`showDialog(...)`](#showdialog)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### showDialog(...)

```typescript
showDialog(options: DialogOptions) => Promise<{ value: string; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#dialogoptions">DialogOptions</a></code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### Interfaces


#### DialogOptions

| Prop          | Type                        |
| ------------- | --------------------------- |
| **`buttons`** | <code>DialogButton[]</code> |


#### DialogButton

| Prop       | Type                               |
| ---------- | ---------------------------------- |
| **`text`** | <code>string</code>                |
| **`role`** | <code>'default' \| 'cancel'</code> |

</docgen-api>
