from django.db import models
from users.models import MyUser

class Video(models.Model):
    title = models.CharField(max_length=255)
    description = models.TextField()
    video_file = models.FileField(upload_to='videos/')
    thamnail = models.ImageField(upload_to='videos/thamnail/')
    uploaded_by = models.ForeignKey(MyUser, on_delete=models.CASCADE)
    uploaded_at = models.DateTimeField(auto_now_add=True)
    likes = models.ManyToManyField(MyUser, related_name='liked_videos', blank=True)
    dislikes = models.ManyToManyField(MyUser, related_name='disliked_videos', blank=True)
    favorites = models.ManyToManyField(MyUser, related_name='favorite_videos', blank=True)

    def total_likes(self):
        return self.likes.count()

    def total_dislikes(self):
        return self.dislikes.count()

    def __str__(self):
        return self.title

class VideoComment(models.Model):
    video = models.ForeignKey(Video, on_delete=models.CASCADE, related_name='comments')
    user = models.ForeignKey(MyUser, on_delete=models.CASCADE)
    text = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
