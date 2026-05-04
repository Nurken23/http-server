<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Категории</title>
  <link rel="stylesheet" href="css/categories_styles.css">
</head>
<body>

<div class="container">
  <h1 class="title">Список категорий</h1>

    <#if categories?has_content>
      <ul class="category-list">

          <#list categories as category>
            <li class="category-item">

              <div class="category-card">
                <div class="category-header">
                    <span class="category-title">
                        ${category.title}
                    </span>

                    <#if category.parent??>
                      <span class="category-parent">
                          Родитель: ${category.parent.title}
                      </span>
                    <#else>
                      <span class="category-root">Корневая</span>
                    </#if>
                </div>

                <div class="category-meta">
                  ID: ${category.id}
                </div>
              </div>
            </li>
          </#list>
      </ul>
    <#else>
      <p class="empty">Категории отсутствуют</p>
    </#if>
</div>

</body>
</html>