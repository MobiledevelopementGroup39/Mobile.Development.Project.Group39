from django.contrib import admin
from .models import Video, VideoComment

@admin.register(Video)
class VideoAdmin(admin.ModelAdmin):
    list_display = ('title', 'uploaded_by', 'uploaded_at')
    search_fields = ('title', 'uploaded_by__username')

admin.site.register(VideoComment)
