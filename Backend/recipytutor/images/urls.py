from django.urls import path
from . import views

urlpatterns = [
    path('', views.image_list, name='image_list'),
    path('upload/', views.upload_image, name='upload_image'),
    path('<int:image_id>/like/', views.like_image, name='like_image'),
    path('<int:image_id>/dislike/', views.dislike_image, name='dislike_image'),
    path('<int:image_id>/comment/', views.add_image_comment, name='add_image_comment'),
]
